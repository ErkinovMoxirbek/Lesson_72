package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
@Entity
@Table(name = "student_course_mark")
public class StudentCourseMarkEntity {
//    id,studentId,courseId,mark,createdDate,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity studentId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity courseId;
    private String mark;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now() ;

}
