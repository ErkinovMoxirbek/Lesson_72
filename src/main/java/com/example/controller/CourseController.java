package com.example.controller;

import com.example.dto.BetweenDate;
import com.example.dto.BetweenPrice;
import com.example.dto.CourseDTO;
import com.example.exp.AppBadRequestException;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "course")
public class CourseController {
    @Autowired
    private CourseService service;
    @PutMapping(value = "/create")
    public ResponseEntity<?> create (@RequestBody CourseDTO dto){
        try {
            return ResponseEntity.ok(service.create(dto));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/list")
    public ResponseEntity<?> getAll (){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<?> getById (@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(service.getById(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-name/{name}")
    public ResponseEntity<?> getByName (@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(service.getByName(name));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-price/{price}")
    public ResponseEntity<?> getByPrice (@PathVariable("price") Double price){
        try {
            return ResponseEntity.ok(service.getByPrice(price));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-duration/{duration}")
    public ResponseEntity<?> getByDuration (@PathVariable("duration") Integer duration){
        try {
            return ResponseEntity.ok(service.getByDuration(duration));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,@RequestBody CourseDTO dto){
        try {
            return ResponseEntity.ok(service.update(id,dto));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(service.delete(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-between-price")
    public ResponseEntity<?> getByBetweenDate (@RequestBody BetweenPrice betweenPrice){
        try {
            return ResponseEntity.ok(service.getByBetweenPrice(betweenPrice));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-between-date")
    public ResponseEntity<?> getByBetweenDate (@RequestBody BetweenDate betweenDate){
        try {
            return ResponseEntity.ok(service.getByBetweenCreatedDate(betweenDate));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
