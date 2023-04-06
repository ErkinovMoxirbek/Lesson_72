package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourseRepository  extends CrudRepository<CourseEntity,Integer > {
    @Override
    <S extends CourseEntity> S save(S entity);

    @Override
    <S extends CourseEntity> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<CourseEntity> findById(Integer integer);
    CourseEntity findByName (String name);
    CourseEntity findByPrice (Double price);
    CourseEntity findByDuration (Integer duration);
    List<CourseEntity> findAllByPriceBetween (Double start ,Double finish);
    List<CourseEntity> findAllByCreatedDateBetween (LocalDateTime start , LocalDateTime finish);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<CourseEntity> findAll();

    @Override
    Iterable<CourseEntity> findAllById(Iterable<Integer> integers);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(CourseEntity entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    void deleteAll(Iterable<? extends CourseEntity> entities);

    @Override
    void deleteAll();
}
