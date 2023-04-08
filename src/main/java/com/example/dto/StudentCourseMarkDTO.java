package com.example.dto;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class StudentCourseMarkDTO {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private String mark;
}
