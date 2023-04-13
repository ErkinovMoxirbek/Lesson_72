package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Entity
@Table(name = "Course")
@Getter @Setter
@ToString
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private Integer duration;
    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now();
}
