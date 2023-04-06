package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.dto.StudentCourseMarkDTO;
import com.example.exp.AppBadRequestException;
import com.example.service.CourseService;
import com.example.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
