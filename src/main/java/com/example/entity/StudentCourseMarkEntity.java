package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "student_course_mark")
@ToString
public class StudentCourseMarkEntity {
//    id,studentId,courseId,mark,createdDate,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "course_id")
    private Integer courseId;
    private Integer mark;
    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now() ;

}
