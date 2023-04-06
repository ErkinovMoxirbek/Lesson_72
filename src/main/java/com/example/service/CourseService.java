package com.example.service;

import com.example.dto.BetweenDate;
import com.example.dto.BetweenPrice;
import com.example.dto.CourseDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public CourseDTO create(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (dto.getPrice() == null ) {
            throw new AppBadRequestException("price qani?");
        }if (dto.getDuration() == null ) {
            throw new AppBadRequestException("duration qani?");
        }
        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public List<CourseEntity> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseEntity> list = new LinkedList<>();
        iterable.forEach(studentEntity -> {
            CourseEntity entity  = new CourseEntity();
            entity.setName(studentEntity.getName());
            entity.setPrice(studentEntity.getPrice());
            entity.setCreatedDate(studentEntity.getCreatedDate());
            entity.setDuration(studentEntity.getDuration());
            entity.setId(studentEntity.getId());
            list.add(entity);
        });
        return list;
    }

    public CourseEntity getById(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadRequestException("Student not found :" + id);
        }
        return optional.get();
    }

    public Boolean update(Integer id, CourseDTO studentDTO) {
        CourseEntity entity = getById(id);
        if (studentDTO.getName() != null ){
            entity.setName(studentDTO.getName());
            System.out.println("update name");
        }
        if (studentDTO.getPrice() != null){
            entity.setPrice(studentDTO.getPrice());
            System.out.println("update price");
        }
        if (studentDTO.getDuration() != null ){
            entity.setDuration(studentDTO.getDuration());
            System.out.println("update duration");
        }

        courseRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        CourseEntity entity = getById(id);
        courseRepository.delete(entity);
        return true;
    }
    public CourseEntity getByName(String name) {
        CourseEntity optional = courseRepository.findByName(name);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + name);
        }
        return optional;
    } public CourseEntity getByPrice(Double price) {
        CourseEntity optional = courseRepository.findByPrice(price);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + price);
        }
        return optional;
    } public CourseEntity getByDuration(Integer duration) {
        CourseEntity optional = courseRepository.findByDuration(duration);
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + duration);
        }
        return optional;
    }
    public List<CourseEntity> getByBetweenPrice(BetweenPrice betweenPrice) {
        List<CourseEntity> optional = courseRepository.findAllByPriceBetween(betweenPrice.getStart(),betweenPrice.getFinish());
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + betweenPrice.toString());
        }
        return optional;
    }
    public List<CourseEntity> getByBetweenCreatedDate(BetweenDate betweenDate) {
        List<CourseEntity> optional = courseRepository.findAllByCreatedDateBetween(betweenDate.getStart(),betweenDate.getFinish());
        if (optional == null){
            throw new AppBadRequestException("Student not found :" + betweenDate.toString());
        }
        return optional;
    }


}
