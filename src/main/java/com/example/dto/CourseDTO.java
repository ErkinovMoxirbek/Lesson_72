package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
public class CourseDTO {
//    id,name,price,duration,createdDate
    private Integer id;
    private String name;
    private Double price;
    private Integer duration;
    private LocalDate createdDate;
}
