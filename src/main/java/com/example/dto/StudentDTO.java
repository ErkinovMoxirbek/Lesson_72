package com.example.dto;

import com.example.enums.Gender;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Gender gender;
    private Integer level;
    private LocalDate CreatedDate;
}
