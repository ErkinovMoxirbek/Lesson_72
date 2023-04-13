package com.example.service;

import com.example.dto.BetweenDate;
import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.enums.Gender;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public Integer create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());

        studentRepository.save(entity);
        return entity.getId();
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();

        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setGender(entity.getGender());

            dtoList.add(dto);
        };
        return dtoList;
    }

    public StudentDTO getById(Integer id) {
        StudentEntity entity = get(id);
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setGender(entity.getGender());
        return dto;
    }
    public List<StudentDTO> toDTO(List<StudentEntity> list){
        List<StudentDTO> dtos = new LinkedList<>();
        for(StudentEntity entity : list){
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setGender(entity.getGender());
            dtos.add(dto);
        }
        return dtos;
    }
    public StudentEntity get(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }
    public Boolean update(Integer id, StudentDTO studentDto) {
        StudentEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }

        entity.setName(studentDto.getName());
        entity.setSurname(studentDto.getSurname());
        entity.setAge(studentDto.getAge());
        entity.setCreatedDate(studentDto.getCreatedDate());
        entity.setLevel(studentDto.getLevel());
        entity.setGender(studentDto.getGender());

        studentRepository.save(entity);
        return true;
    }
    public Boolean delete(Integer id) {
        StudentEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        studentRepository.delete(entity);
        return true;
    }

    public List<StudentDTO> getByName(String name) {
        List<StudentEntity> list = studentRepository.findByName(name);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this name was found: " + name);
        }
        return toDTO(list);
    }
    public List<StudentDTO> getBySurname(String surname) {
        List<StudentEntity> list = studentRepository.findBySurname(surname);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this surname was found: " + surname);
        }
        return toDTO(list);
    }
    public List<StudentDTO> getByLevel(Integer level) {
        List<StudentEntity> list = studentRepository.findByLevel(level);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this level was found: " + level);
        }
        return toDTO(list);
    }
    public List<StudentDTO> getByAge(Integer age) {
        List<StudentEntity> list = studentRepository.findByAge(age);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this age was found: " + age);
        }
        return toDTO(list);
    }
    public List<StudentDTO> getByGender(Gender gender) {
        List<StudentEntity> list = studentRepository.findByGender(gender);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this gender was found: " + gender);
        }
        return toDTO(list);
    }
    public List<StudentDTO> getByCreatedDate(LocalDate date) {
        List<StudentEntity> list = studentRepository.findByCreatedDate(date);
        if (list.isEmpty()){
            throw new AppBadRequestException("No student with this gender was found: " + date);
        }
        return toDTO(list);
    }

    public List<StudentDTO> getByBetweenCreatedDate(LocalDate date1, LocalDate date2) {
        List<StudentEntity> list = studentRepository.findByCreatedDateBetween(date1, date2);
        if (list.isEmpty()){
            throw new AppBadRequestException("No student with this gender was found: ");
        }
        return toDTO(list);
    }

    public Page<StudentDTO> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAll(paging);

        Long totalCount = pageObj.getTotalElements();

        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();

        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setGender(entity.getGender());
            dtoList.add(dto);
        }

        Page<StudentDTO> response = new PageImpl<StudentDTO>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentDTO> paginationWithName(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByName(name, paging);

        Long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setGender(entity.getGender());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<StudentDTO>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentDTO> paginationWithLevel(Integer level, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByLevel(level, paging);

        Long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setGender(entity.getGender());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<StudentDTO>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentDTO> paginationWithGender(Gender gender, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);

        Page<StudentEntity> pageObj = studentRepository.findAllByGender(gender, paging);
        Long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setAge(entity.getAge());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setGender(entity.getGender());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<StudentDTO>(dtoList, paging, totalCount);
        return response;

    }
}
