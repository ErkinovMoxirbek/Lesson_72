package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.dto.StudentCourseMarkDTO;
import com.example.exp.AppBadRequestException;
import com.example.service.CourseService;
import com.example.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/SCM")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService service;
    @PutMapping(value = "/create")
    public ResponseEntity<?> create (@RequestBody StudentCourseMarkDTO dto){
        try {
            return ResponseEntity.ok(service.create(dto));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
