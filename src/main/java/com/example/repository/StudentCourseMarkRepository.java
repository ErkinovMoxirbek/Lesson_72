package com.example.repository;

import com.example.entity.StudentCourseMarkEntity;
import org.springframework.data.repository.CrudRepository;

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
    Iterable<StudentCourseMarkEntity> findAllById(Iterable<Integer> integers);

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
}
