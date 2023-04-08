package com.example.service;

import com.example.dto.BetweenDate;
import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.enums.Gender;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (dto.getSurname() == null || dto.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }if (dto.getAge() == null || dto.getAge() == 0) {
            throw new AppBadRequestException("age qani?");
        }if (dto.getLevel() == null) {
            throw new AppBadRequestException("level qani?");
        }
        System.out.println(entity.toString());
        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public List<StudentEntity> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentEntity> list = new LinkedList<>();
        iterable.forEach(studentEntity -> {
            StudentEntity entity  = new StudentEntity();
            entity.setName(studentEntity.getName());
            entity.setSurname(studentEntity.getSurname());
            entity.setCreatedDate(studentEntity.getCreatedDate());
            entity.setGender(studentEntity.getGender());
            entity.setAge(studentEntity.getAge());
            entity.setId(studentEntity.getId());
            list.add(entity);
        });
        return list;
    }
    public StudentEntity getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadRequestException("Student not found :" + id);
        }
        return optional.get();
    }
    public Boolean update(Integer id, StudentDTO studentDTO) {
        StudentEntity entity = getById(id);
        if (studentDTO.getName() != null ){
            entity.setName(studentDTO.getName());
            System.out.println("update name");
        }
        if (studentDTO.getSurname() != null){
            entity.setSurname(studentDTO.getSurname());
            System.out.println("update surname");
        }
        if (studentDTO.getGender() != null ){
            entity.setGender(studentDTO.getGender());
            System.out.println("update gender");
        }
        if (studentDTO.getAge() != null ){
            entity.setAge(studentDTO.getAge());
            System.out.println("update age");
        }
        studentRepository.save(entity);
        return true;
    }
    public Boolean delete(Integer id) {
        StudentEntity entity = getById(id);
        studentRepository.delete(entity);
        return true;
    }
    public StudentEntity getByLevel(Integer level) {
        StudentEntity optional = studentRepository.findByLevel(level);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + level);
        }
        return optional;
    }
    public StudentEntity getBySurname(String surname) {
        StudentEntity optional = studentRepository.findBySurname(surname);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + surname);
        }
        return optional;
    }
    public StudentEntity getByName(String name) {
        StudentEntity optional = studentRepository.findByName(name);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + name);
        }
        return optional;
    }
    public StudentEntity getByGender(Gender gender) {
        StudentEntity optional = studentRepository.findByGender(gender);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + gender);
        }
        return optional;
    }
    public StudentEntity getByAge(Integer age) {
        StudentEntity optional = studentRepository.findByAge(age);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + age);
        }
        return optional;
    }
    public List<StudentEntity> getByCreatedDate(LocalDateTime createdDate) {
        List<StudentEntity> optional = studentRepository.findAllByCreatedDate(createdDate);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + createdDate);
        }
        return optional;
    }
    public List<StudentEntity> getByBetweenCreatedDate(BetweenDate betweenDate) {
        List<StudentEntity> optional = studentRepository.findAllByCreatedDateBetween(betweenDate.getStart(),betweenDate.getFinish());
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + betweenDate.toString());
        }
        return optional;
    }
}
