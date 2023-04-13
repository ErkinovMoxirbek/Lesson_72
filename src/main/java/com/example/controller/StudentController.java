package com.example.controller;

import com.example.dto.BetweenDate;
import com.example.dto.StudentDTO;
import com.example.enums.Gender;
import com.example.exp.AppBadRequestException;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PutMapping(value = "/create")
    public ResponseEntity<?> create (@RequestBody StudentDTO studentDTO){
        try {
            return ResponseEntity.ok(studentService.create(studentDTO));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/list")
    public ResponseEntity<?> getAll (){
        return ResponseEntity.ok(studentService.getAll());
    }
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<?> getById (@PathVariable("id") Integer id){
        try {
            System.out.println(id);
            return ResponseEntity.ok(studentService.getById(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-name/{name}")
    public ResponseEntity<?> getByName (@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(studentService.getByName(name));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-surname/{surname}")
    public ResponseEntity<?> getBySurname (@PathVariable("surname") String surname){
        try {
            return ResponseEntity.ok(studentService.getBySurname(surname));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-level/{level}")
    public ResponseEntity<?> getByLevel (@PathVariable("level") Integer level){
        try {
            return ResponseEntity.ok(studentService.getByLevel(level));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-age/{age}")
    public ResponseEntity<?> getByAge (@PathVariable("age") Integer age){
        try {
            return ResponseEntity.ok(studentService.getByAge(age));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-gender/{gender}")
    public ResponseEntity<?> getByGender (@PathVariable("gender") Gender gender){
        try {
            return ResponseEntity.ok(studentService.getByGender(gender));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-created-date/{createdDate}")
    public ResponseEntity<?> getByCreatedDate (@PathVariable("createdDate") LocalDate createdDate){
        try {
            return ResponseEntity.ok(studentService.getByCreatedDate(createdDate));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-between-date")
    public ResponseEntity<?> getByBetweenDate (@RequestBody BetweenDate betweenDate){
        try {
            return ResponseEntity.ok(studentService.getByBetweenCreatedDate(betweenDate.getStart(),betweenDate.getFinish()));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,@RequestBody StudentDTO studentDTO){
        try {
            return ResponseEntity.ok(studentService.update(id,studentDTO));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(studentService.delete(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/paging")
    public ResponseEntity<Page<StudentDTO>> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(studentService.pagination(page, size));
    }

    @GetMapping(value = "/paging-level/{level}")
    public ResponseEntity<Page<StudentDTO>> pagingWithLevel(@RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "3") int size,
                                                            @PathVariable("level") Integer level) {
        Page<StudentDTO> response = studentService.paginationWithLevel(level, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/paging-gender/{gender}")
    public ResponseEntity<Page<StudentDTO>> pagingWithGender(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "3") int size,
                                                             @PathVariable("gender") Gender gender) {
        Page<StudentDTO> response = studentService.paginationWithGender(gender, page, size);
        return ResponseEntity.ok(response);
    }
}
