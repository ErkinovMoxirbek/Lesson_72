package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter @Setter
public class BetweenDate {
    private LocalDate start;
    private LocalDate finish;
}
