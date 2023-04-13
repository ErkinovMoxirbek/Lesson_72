package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.StudentCourseMarkDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.mapper.CourseInfoMapper;
import com.example.repository.StudentCourseMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository repository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    public Integer create(StudentCourseMarkDTO dto) {
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();

        StudentEntity student = studentService.get(dto.getStudentId());
        if (student == null) {
            throw new AppBadRequestException("Student not found: " + dto.getStudentId());
        }
        CourseEntity course = courseService.get(dto.getCourseId());
        if (course == null) {
            throw new AppBadRequestException("Course not found: " + dto.getCourseId());
        }

        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDateTime.now());

        repository.save(entity);
        dto.setId(entity.getId());
        return entity.getId();
    }

    public Boolean update(Integer id, StudentCourseMarkDTO dto) {
        StudentCourseMarkEntity entity = get1(id);
        if (entity == null) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setMark(dto.getMark());

        repository.save(entity);
        return true;
    }

    public StudentCourseMarkEntity get1(Integer id) {
        Optional<StudentCourseMarkEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        return optional.get();
    }

    public StudentCourseMarkDTO getById(Integer id) {
        StudentCourseMarkEntity entity = get1(id);
        if (entity == null) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId());
        dto.setCourseId(entity.getCourseId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStudentName(entity.getStudent().getName());
        dto.setStudentSurname(entity.getStudent().getSurname());
        dto.setCourseName(entity.getCourse().getName());
        return dto;
    }

    public List<StudentCourseMarkDTO> getByIdWithDetail(Integer id) {
        List<StudentCourseMarkDTO> list = new LinkedList<>();

        repository.findAllById(Collections.singleton(id)).forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());

            dto.setStudentId(entity.getStudentId());
            dto.setStudentName(entity.getStudent().getName());
            dto.setStudentSurname(entity.getStudent().getSurname());

            dto.setCourseId(entity.getCourseId());
            dto.setCourseName(entity.getCourse().getName());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public Boolean deleteById(Integer id) {
        StudentCourseMarkEntity entity = get1(id);
        if (entity == null) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        repository.deleteById(id);
        return true;
    }

    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> iterable = repository.findAll();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : iterable) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId());
            dto.setCourseId(entity.getCourseId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
//            dto.setStudentName(entity.getStudent().getName());
//            dto.setStudentSurname(entity.getStudent().getSurname());
//            dto.setCourseName(entity.getCourse().getName());
            dtoList.add(dto);
        };
        return dtoList;
    }

    public List<StudentCourseMarkDTO> toDTO(List<StudentCourseMarkEntity> list){
        List<StudentCourseMarkDTO> dtos = new LinkedList<>();
        for(StudentCourseMarkEntity entity : list){
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId());
            dto.setCourseId(entity.getCourseId());
            dto.setMark(entity.getMark());
            dtos.add(dto);
        }
        return dtos;
    }

    public StudentCourseMarkDTO getByDate(StudentEntity student_id, LocalDateTime date) {
        StudentCourseMarkEntity entity = repository.
                findByStudentIdAndCreatedDateOrderByMark(student_id, date);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setStudentId(entity.getStudentId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStudentName(entity.getStudent().getName());
        dto.setStudentSurname(entity.getStudent().getSurname());
        dto.setCourseName(entity.getCourse().getName());
        return dto;
    }

    public List<StudentCourseMarkDTO> getStudentCourseMarkListBetweenDates(StudentEntity studentId, LocalDateTime fromDate, LocalDateTime toDate) {
        Iterable<StudentCourseMarkEntity> iterable = repository.
                findAllByStudentIdAndCreatedDateBetween(studentId, fromDate,toDate);
        List<StudentCourseMarkDTO> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setStudentName(entity.getStudent().getName());
            dto.setStudentSurname(entity.getStudent().getSurname());
            dto.setCourseName(entity.getCourse().getName());
            studentDTOLinkedList.add(dto);
        });

        return studentDTOLinkedList;
    }

    public List<StudentCourseMarkDTO> getAllStudentMark(StudentEntity studentId) {
        Iterable<StudentCourseMarkEntity> iterable = repository.
                findByStudentIdOrderByMarkDesc(studentId);
        List<StudentCourseMarkDTO> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setStudentName(entity.getStudent().getName());
            dto.setStudentSurname(entity.getStudent().getSurname());
            dto.setCourseName(entity.getCourse().getName());
            studentDTOLinkedList.add(dto);
        });

        return studentDTOLinkedList;
    }

    public List<StudentCourseMarkDTO> getDateMark(StudentEntity studentId, CourseEntity courseId) {
        Iterable<StudentCourseMarkEntity> iterable = repository.
                findByStudentIdAndCourseIdOrderByCourseIdDescMark(studentId, courseId);
        List<StudentCourseMarkDTO> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setStudentName(entity.getStudent().getName());
            dto.setStudentSurname(entity.getStudent().getSurname());
            dto.setCourseName(entity.getCourse().getName());
            studentDTOLinkedList.add(dto);
        });

        return studentDTOLinkedList;
    }

    public StudentCourseMarkDTO getFirstMark(StudentEntity id) {
        StudentCourseMarkEntity entity = repository.findFirstByStudentIdOrderByMarkAsc(id);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setStudentId(entity.getStudentId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStudentName(entity.getStudent().getName());
        dto.setStudentSurname(entity.getStudent().getSurname());
        dto.setCourseName(entity.getCourse().getName());

        return dto;
    }
    public StudentCourseMarkDTO getLastMark(StudentEntity id) {
        StudentCourseMarkEntity entity = repository.findFirstByStudentIdOrderByMark(id);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setStudentId(entity.getStudentId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStudentName(entity.getStudent().getName());
        dto.setStudentSurname(entity.getStudent().getSurname());
        dto.setCourseName(entity.getCourse().getName());
        return dto;
    }

    public StudentCourseMarkDTO getStudentCurseFirstMark(StudentEntity id, CourseEntity course) {
        StudentCourseMarkEntity entity = repository.findFirstByStudentIdAndAndCourseIdOrderByMark(id,course);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setStudentId(entity.getStudentId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStudentName(entity.getStudent().getName());
        dto.setStudentSurname(entity.getStudent().getSurname());
        dto.setCourseName(entity.getCourse().getName());
        return dto;
    }

    public Integer countCourseMark(CourseEntity course) {
        Integer count = repository.countByCourseIdOrderByMark(course);
        return count;
    }
    public void test() {
        List<Object[]> courseObjList = repository.findLastCourseMarkerAsNative(1);
        if (courseObjList.size() > 0) {
            Object[] courseObj = courseObjList.get(0);

            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId((Integer) courseObj[0]);
            courseDTO.setName((String) courseObj[1]);
            System.out.println(courseDTO);
        }

        System.out.println("dasda");
    }

    public void test2() {
        CourseInfoMapper courseInfoMapper = repository.findLastCourseMarkerAsNativeMapping(1);
        if (courseInfoMapper != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseInfoMapper.getCId());
            courseDTO.setName(courseInfoMapper.getCName());
            System.out.println(courseDTO +" "+ courseInfoMapper.getMark());
        }

        System.out.println("dasda");
    }

    public Page<StudentCourseMarkDTO> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<StudentCourseMarkEntity> page1 = repository.findAll(pageable);
        Long totalCount = page1.getTotalElements();
        List<StudentCourseMarkEntity> entityList = page1.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setStudentName(entity.getStudent().getName());
            dto.setStudentSurname(entity.getStudent().getSurname());
            dto.setCourseName(entity.getCourse().getName());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDTO> response = new PageImpl<StudentCourseMarkDTO>(dtoList, pageable, totalCount);
        return response;
    }
    public Page<StudentCourseMarkDTO> pagingByStudentIdWithCreatedDate(Integer id, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<StudentCourseMarkEntity> page1 = repository.findAllByStudentId(id, pageable);
        Long totalCount = page1.getTotalElements();
        List<StudentCourseMarkEntity> entityList = page1.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setStudentName(entity.getStudent().getName());
            dto.setStudentSurname(entity.getStudent().getSurname());
            dto.setCourseName(entity.getCourse().getName());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDTO> response = new PageImpl<StudentCourseMarkDTO>(dtoList, pageable, totalCount);
        return response;
    }

    public Page<StudentCourseMarkDTO> pagingByCourseIdWithCreatedDate(Integer id, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<StudentCourseMarkEntity> page1 = repository.findAllByCourseId(id, pageable);
        Long totalCount = page1.getTotalElements();
        List<StudentCourseMarkEntity> entityList = page1.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setStudentName(entity.getStudent().getName());
            dto.setStudentSurname(entity.getStudent().getSurname());
            dto.setCourseName(entity.getCourse().getName());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDTO> response = new PageImpl<StudentCourseMarkDTO>(dtoList, pageable, totalCount);
        return response;
    }
}
