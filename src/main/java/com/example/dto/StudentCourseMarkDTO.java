package com.example.dto;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class StudentCourseMarkDTO {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private String mark;
    private LocalDateTime localDateTime;
}
