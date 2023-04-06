package com.example.service;

import com.example.dto.StudentCourseMarkDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import com.example.repository.StudentCourseMarkRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    public StudentCourseMarkDTO create(StudentCourseMarkDTO dto){
        Optional<CourseEntity> optionalC = courseRepository.findById(dto.getCourseId());
        Optional<StudentEntity> optionalS = studentRepository.findById(dto.getStudentId());
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setCourseId(optionalC.get());
        entity.setStudentId(optionalS.get());
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
}
