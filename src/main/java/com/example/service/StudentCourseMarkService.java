package com.example.service;

import com.example.dto.StudentCourseMarkDTO;
import com.example.entity.StudentCourseMarkEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentCourseMarkRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository repository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    public StudentCourseMarkDTO create(StudentCourseMarkDTO dto){
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setCourseId(dto.getCourseId());
        entity.setStudentId(dto.getStudentId());
        entity.setMark(dto.getMark());
        if (dto.getCourseId() == null || dto.getCourseId() == 0) {
            throw new AppBadRequestException("course_id qani?");
        }
        if (dto.getStudentId() == null || dto.getStudentId() == 0) {
            throw new AppBadRequestException("Student id qani?");
        }
        repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public Boolean update(Integer id, StudentCourseMarkDTO dto) {
        if (id == null || id == 0) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        StudentCourseMarkEntity entity = getById(id);
        if (dto.getCourseId() != null && !dto.getCourseId().equals(0)){

            System.out.println("update courseId");
            entity.setCourseId(dto.getCourseId());
        }
        if (dto.getStudentId() != null && !dto.getStudentId().equals(0)){
            System.out.println("update studentId");
            entity.setStudentId(dto.getStudentId());
        }
        if (dto.getMark() != null){
            System.out.println("update mark");
            entity.setMark(dto.getMark());
        }
        repository.save(entity);
        return true;
    }
    public Boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }
    public StudentCourseMarkEntity getById(Integer id) {
        Optional<StudentCourseMarkEntity> optional = repository.findById(id);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + id);
        }
        return optional.get();
    }
    public List<StudentCourseMarkEntity> getByStudentId(Integer id) {
        List<StudentCourseMarkEntity> optional = repository.findByStudentId(id);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + id);
        }
        return optional;
    }
    public List<StudentCourseMarkEntity> getByCourseId(Integer id) {
        List<StudentCourseMarkEntity> optional = repository.findByCourseId(id);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + id);
        }
        return optional;
    }
    public List<StudentCourseMarkEntity> getByCreatedDate(LocalDate date) {
        List<StudentCourseMarkEntity> optional = repository.findByCreatedDate(date);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + date);
        }
        return optional;
    }
    public List<StudentCourseMarkEntity> getByMark(Integer mark) {
        List<StudentCourseMarkEntity> optional = repository.findByMark(mark);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + mark);
        }
        return optional;
    }
    public List<StudentCourseMarkDTO> getByIdWithDetail(Integer id) {
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        repository.findAllById(id).forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());

            dto.setStudentId(entity.getStudentId());
            dto.setStudentName(studentService.getById(entity.getStudentId()).getName());
            dto.setStudentSurname(studentService.getById(entity.getStudentId()).getSurname());

            dto.setCourseId(entity.getCourseId());
            dto.setCourseName(courseService.getById(entity.getCourseId()).getName());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }
    public List<StudentCourseMarkDTO> getByCreatedDateWithDetail(LocalDate date) {
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        repository.findAllByCreatedDate(date).forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());

            dto.setStudentId(entity.getStudentId());
            dto.setStudentName(studentService.getById(entity.getStudentId()).getName());
            dto.setStudentSurname(studentService.getById(entity.getStudentId()).getSurname());

            dto.setCourseId(entity.getCourseId());
            dto.setCourseName(courseService.getById(entity.getCourseId()).getName());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }
    public List<StudentCourseMarkDTO> getAll(){
        Iterable<StudentCourseMarkEntity> iterable = repository.findAll();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : iterable) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId());
            dto.setCourseId(entity.getCourseId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setStudentName(studentService.getById(entity.getStudentId()).getName());
            dto.setStudentSurname(studentService.getById(entity.getStudentId()).getSurname());
            dto.setCourseName(courseService.getById(entity.getCourseId()).getName());
            dtoList.add(dto);
        };
        return dtoList;
    }
    public List<StudentCourseMarkDTO> getByCreatedDateBetweenWithDetail(LocalDate to,LocalDate from){
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        repository.findAllByCreatedDateBetween(to,from).forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());

            dto.setStudentId(entity.getStudentId());
            dto.setStudentName(studentService.getById(entity.getStudentId()).getName());
            dto.setStudentSurname(studentService.getById(entity.getStudentId()).getSurname());

            dto.setCourseId(entity.getCourseId());
            dto.setCourseName(courseService.getById(entity.getCourseId()).getName());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }
    public List<StudentCourseMarkDTO> getByStudentIdAllMark(Integer id){
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        repository.findAllByStudentId(id).forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());

            dto.setStudentId(entity.getStudentId());
            dto.setStudentName(studentService.getById(entity.getStudentId()).getName());
            dto.setStudentSurname(studentService.getById(entity.getStudentId()).getSurname());

            dto.setCourseId(entity.getCourseId());
            dto.setCourseName(courseService.getById(entity.getCourseId()).getName());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }
    public List<StudentCourseMarkDTO> getByCourseIdAllMark(Integer id){
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        repository.findAllByCourseId(id).forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());

            dto.setStudentId(entity.getStudentId());
            dto.setStudentName(studentService.getById(entity.getStudentId()).getName());
            dto.setStudentSurname(studentService.getById(entity.getStudentId()).getSurname());

            dto.setCourseId(entity.getCourseId());
            dto.setCourseName(courseService.getById(entity.getCourseId()).getName());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }
    public StudentCourseMarkDTO getByStudentIdLastMark(Integer id){
            StudentCourseMarkEntity entity = repository.findByStudentIdLastMark(id);
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());

            dto.setStudentId(entity.getStudentId());
            dto.setStudentName(studentService.getById(entity.getStudentId()).getName());
            dto.setStudentSurname(studentService.getById(entity.getStudentId()).getSurname());

            dto.setCourseId(entity.getCourseId());
            dto.setCourseName(courseService.getById(entity.getCourseId()).getName());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            return dto;
    }
}
