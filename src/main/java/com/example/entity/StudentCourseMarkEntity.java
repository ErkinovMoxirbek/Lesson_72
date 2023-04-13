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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity student;

    @Column(name = "course_id")
    private Integer courseId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private CourseEntity course;
    private Integer mark;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now() ;

}
