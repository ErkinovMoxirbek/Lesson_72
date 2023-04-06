package com.example.entity;

import com.example.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter @Setter
@Entity
@Table(name = "student")
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
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
}
