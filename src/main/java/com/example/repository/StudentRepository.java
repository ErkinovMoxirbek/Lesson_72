package com.example.repository;

import com.example.entity.StudentEntity;
import com.example.enums.Gender;
import com.example.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity,Integer > ,
        PagingAndSortingRepository<StudentEntity, Integer> {
        List<StudentEntity> findBySurname(String surname);
        List<StudentEntity> findByLevel(Integer level);
        List<StudentEntity> findByAge(Integer age);
        List<StudentEntity> findByBirthDate(LocalDate date);
        List<StudentEntity> findByName(String name);
        List<StudentEntity> findByGender(Gender gender);
        List<StudentEntity> findByBirthDateBetween(LocalDate date1, LocalDate date2);
        Page<StudentEntity> findAllByName(String name, Pageable pageable);

        Page<StudentEntity> findAllByLevel(Integer level, Pageable paging);
        Page<StudentEntity> findAllByGender(Gender gender, Pageable paging);
}
