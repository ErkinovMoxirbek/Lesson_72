package com.example.repository;

import com.example.entity.StudentEntity;
import com.example.enums.Gender;
import com.example.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    List<StudentEntity> findAllByCreatedDate(LocalDate createdDate);
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
    @Transactional
    @Modifying
    @Query("update StudentEntity set visible = :visible where id = :sid")
    Integer changeVisibility(@Param("sid") Integer sid, @Param("visible") Boolean v);

    @Query("from StudentEntity where name like ?1")
    List<StudentEntity> findAllByName2 (String name);
    @Query("select new StudentEntity (id,name,surname) from StudentEntity ")
    List<StudentEntity> findByName4();
//    @Query("SELECT new com.example.mapper.StudentMapper(id,name, phone) FROM StudentEntity ")
//    List<StudentMapper> findByName5();
}
