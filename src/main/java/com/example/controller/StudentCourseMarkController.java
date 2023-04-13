package com.example.controller;

import com.example.dto.StudentCourseMarkDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/SCM")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService service;
    @PostMapping (value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") Integer id,
                                    @RequestBody StudentCourseMarkDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping(value = "/getByIdWithDetail/{id}")
    public ResponseEntity<List<StudentCourseMarkDTO>> getByIdWithDetail(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(service.getByIdWithDetail(id));
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<StudentCourseMarkDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/getByDate")
    public ResponseEntity<?> getByDate(@RequestParam("studentId") Integer id, @RequestParam("created_date") LocalDateTime created_date){
        StudentEntity student = new StudentEntity();
        student.setId(id);
        StudentCourseMarkDTO dto = service.getByDate(student, created_date);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getBetweenDate")
    private ResponseEntity<List<StudentCourseMarkDTO>> getBetweenDate(@RequestParam("studentId") Integer sId,
                                                                  @RequestParam("fromDate") LocalDateTime fromDate,
                                                                  @RequestParam("toDate") LocalDateTime toDate){
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        List<StudentCourseMarkDTO> dto = service.
                getStudentCourseMarkListBetweenDates(student, fromDate, toDate);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getAllMark")
    private ResponseEntity<List<StudentCourseMarkDTO>> getAllMark(@RequestParam("studentId") Integer sId){
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        List<StudentCourseMarkDTO> dto = service.getAllStudentMark(student);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getDateMark")
    private ResponseEntity<List<StudentCourseMarkDTO>> getAllMark(@RequestParam("studentId") Integer sId,
                                                              @RequestParam("courseId") Integer cId){
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        CourseEntity course = new CourseEntity();
        course.setId(cId);
        List<StudentCourseMarkDTO> dto = service.getDateMark(student,course);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/getTopMark/{id}")
    public ResponseEntity<?> getFirstMark(@PathVariable("id") Integer id) {
        StudentEntity student = new StudentEntity();
        student.setId(id);
        StudentCourseMarkDTO dto = service.getFirstMark(student);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getLastMark/{id}")
    public ResponseEntity<?> getLastMark(@PathVariable("id") Integer id) {
        StudentEntity student = new StudentEntity();
        student.setId(id);
        StudentCourseMarkDTO dto = service.getLastMark(student);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getStudentCourseFirstMark")
    public ResponseEntity<?> getLastMark(@RequestParam("studentId") Integer sId,
                                         @RequestParam("courseId") Integer cId) {
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        CourseEntity course = new CourseEntity();
        course.setId(cId);
        StudentCourseMarkDTO dto = service.getStudentCurseFirstMark(student, course);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/countCourseMark")
    public ResponseEntity<?> countCourseMark(@RequestParam("courseId") Integer cId) {
        CourseEntity course = new CourseEntity();
        course.setId(cId);
        Integer count = service.countCourseMark( course);
        return ResponseEntity.ok(count);
    }
    @PostMapping(value = "/paging")
    public ResponseEntity<Page<StudentCourseMarkDTO>> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                                         @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(service.pagination(page, size));
    }

    @PostMapping(value = "/pagingByStudentIdWithCreatedDate/{student_id}")
    public ResponseEntity<Page<StudentCourseMarkDTO>> pagingByStudentIdWithCreatedDate(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                                   @RequestParam(value = "size", defaultValue = "2") int size,
                                                                                   @PathVariable ("student_id") Integer id) {
        return ResponseEntity.ok(service.pagingByStudentIdWithCreatedDate(id, page, size));
    }

    @PostMapping(value = "/pagingByCourseIdWithCreatedDate/{course_id}")
    public ResponseEntity<Page<StudentCourseMarkDTO>> pagingByIdWithCreatedDate(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                            @RequestParam(value = "size", defaultValue = "2") int size,
                                                                            @PathVariable ("course_id") Integer id) {
        return ResponseEntity.ok(service.pagingByCourseIdWithCreatedDate(id, page, size));
    }
}
