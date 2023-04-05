package com.example.repository;

import com.example.entity.StudentEntity;
import com.example.enums.Gender;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity,Integer > {
    @Override
    <S extends StudentEntity> S save(S entity);

    @Override
    <S extends StudentEntity> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<StudentEntity> findById(Integer integer);
    StudentEntity findByLevel(Integer level);
    StudentEntity findBySurname(String surname);
    StudentEntity findByName(String name);
    StudentEntity findByAge(Integer age);
    StudentEntity findByGender(Gender gender);
    List<StudentEntity> findAllByCreatedDate(LocalDateTime createdDate);
    List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime toCreatedDate,LocalDateTime fromCreatedDate);
    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<StudentEntity> findAll();

    @Override
    Iterable<StudentEntity> findAllById(Iterable<Integer> integers);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(StudentEntity entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    void deleteAll(Iterable<? extends StudentEntity> entities);

    @Override
    void deleteAll();
}
