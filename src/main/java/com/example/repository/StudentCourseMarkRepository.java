package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity,Integer > {
    @Override
    <S extends StudentCourseMarkEntity> S save(S entity);

    @Override
    <S extends StudentCourseMarkEntity> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<StudentCourseMarkEntity> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<StudentCourseMarkEntity> findAll();



    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(StudentCourseMarkEntity entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    void deleteAll(Iterable<? extends StudentCourseMarkEntity> entities);

    @Override
    void deleteAll();
    List<StudentCourseMarkEntity> findByCreatedDate (LocalDate date);
    List<StudentCourseMarkEntity> findByMark (Integer mark);
    List<StudentCourseMarkEntity> findByStudentId ( Integer id);
    List<StudentCourseMarkEntity> findByCourseId (Integer id);
    List<StudentCourseMarkEntity> findAllById(Integer id);
    List<StudentCourseMarkEntity> findAllByCreatedDate(LocalDate date);
    List<StudentCourseMarkEntity> findAllByCreatedDateBetween(LocalDate to,LocalDate from);
    List<StudentCourseMarkEntity> findAllByStudentId(Integer id);
    List<StudentCourseMarkEntity> findAllByCourseId(Integer id);
    @Transactional
    @Modifying
    @Query("select StudentCourseMarkEntity from StudentCourseMarkEntity where createdDate = (select max(createdDate) from StudentCourseMarkEntity where  studentId = :id)")
    StudentCourseMarkEntity findByStudentIdLastMark (@Param("id")Integer id);

}
