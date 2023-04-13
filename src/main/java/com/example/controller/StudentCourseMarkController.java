package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.dto.StudentCourseMarkDTO;
import com.example.exp.AppBadRequestException;
import com.example.service.CourseService;
import com.example.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    @PostMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,@RequestBody StudentCourseMarkDTO dto){
        try {
            return ResponseEntity.ok(service.update(id,dto));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<?> getById (@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(service.getById(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }  @GetMapping(value = "/get-by-student-id/{id}")
    public ResponseEntity<?> getByStudentId (@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(service.getByStudentId(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }  @GetMapping(value = "/get-by-course-id/{id}")
    public ResponseEntity<?> getByCourseId (@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok(service.getByCourseId(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }  @GetMapping(value = "/get-by-mark/{mark}")
    public ResponseEntity<?> getByMark (@PathVariable("mark") Integer mark){
        try {
            return ResponseEntity.ok(service.getByMark(mark));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    } @GetMapping(value = "/get-by-created-date/{date}")
    public ResponseEntity<?> getByCreatedDate (@PathVariable("date") LocalDate date){
        try {
            return ResponseEntity.ok(service.getByCreatedDate(date));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-id-with-detail/{id}")
    public ResponseEntity<?> getByIdWithDetail(@PathVariable ("id") Integer id) {
        try {
            return ResponseEntity.ok(service.getByIdWithDetail(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    } @GetMapping(value = "/get-by-created-date-with-detail/{date}")
    public ResponseEntity<?> getByCreatedDateWithDetail(@PathVariable ("date") LocalDate date) {
        try {
            return ResponseEntity.ok(service.getByCreatedDateWithDetail(date));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.delete(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/list")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/get-by-created-date-between-with-detail")
    public ResponseEntity<?> getByCreatedDateBetweenWithDetail(@RequestParam("to") LocalDate to,
                                                              @RequestParam("from") LocalDate from) {
        return ResponseEntity.ok(service.getByCreatedDateBetweenWithDetail(to,from));
    }
    @GetMapping(value = "/get-by-sudent-id-all-mark/{id}")
    public ResponseEntity<?> getByStudentIdAllMark(@PathVariable("id")Integer id){
        try {
            return ResponseEntity.ok(service.getByStudentIdAllMark(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-course-id-all-mark/{id}")
    public ResponseEntity<?> getByCourseIdAllMark(@PathVariable("id")Integer id){
        try {
            return ResponseEntity.ok(service.getByCourseIdAllMark(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = "/get-by-student-id-last-mark/{id}")
    public ResponseEntity<?> getByStudentIdLastMark(@PathVariable("id")Integer id){
        try {
            return ResponseEntity.ok(service.getByStudentIdLastMark(id));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    } @GetMapping(value = "/get-by-student-id-and-course-id-max-mark")
    public ResponseEntity<?> getByStudentIdAndCourseIdMaxMark(@RequestParam("student_id") Integer sid,@RequestParam("course_id")Integer cid){
        try {
            return ResponseEntity.ok(service.getByStudentIdAndCourseIdMaxMark(sid,cid));
        }catch (AppBadRequestException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
