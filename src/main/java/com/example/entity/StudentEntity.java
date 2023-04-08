package com.example.entity;

import com.example.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter @Setter
@Entity
@Table(name = "student")
@ToString
public class StudentEntity {
//    id,name,surname,level,age,Gender,createdDate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private Gender gender;
    private Boolean visible = true;
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate = LocalDate.now();

    public StudentEntity(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public StudentEntity() {
    }
}
