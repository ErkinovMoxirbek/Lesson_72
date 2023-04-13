package com.example.service;

import com.example.dto.BetweenDate;
import com.example.dto.BetweenPrice;
import com.example.dto.CourseDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        entity.setCreatedDate(dto.getCreatedDate());

        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        });
        return dtoList;
    }
    public CourseDTO getById(Integer id) {
        CourseEntity entity = get(id);
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public List<CourseDTO> toDTO(List<CourseEntity> entityList){
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }
    public CourseEntity get(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Course not found: " + id);
        }
        return optional.get();
    }
    public Boolean updateById(Integer id, CourseDTO courseDto) {
        CourseEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }

        entity.setName(courseDto.getName());
        entity.setPrice(courseDto.getPrice());
        entity.setDuration(courseDto.getDuration());
        entity.setCreatedDate(courseDto.getCreatedDate());

        courseRepository.save(entity);
        return true;
    }
    public Boolean deleteById(Integer id) {
        CourseEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        courseRepository.delete(entity);
        return true;
    }

    public List<CourseDTO> getByName(String name) {
        List<CourseEntity> list = courseRepository.findByName(name);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this name: " + name);
        }
        return toDTO(list);
    }
    public List<CourseDTO> getByPrice(Double price) {
        List<CourseEntity> list = courseRepository.findByPrice(price);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this price: " + price);
        }
        return toDTO(list);
    }
    public List<CourseDTO> getByDuration(String duration) {
        List<CourseEntity> list = courseRepository.findByDuration(duration);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this duration: " + duration);
        }
        return toDTO(list);
    }

    public List<CourseDTO> getByCourseListPriceBetween(Double price1, Double price2) {
        List<CourseEntity> list = courseRepository.findByPriceBetween(price1, price2);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this prices between: ");
        }
        return toDTO(list);
    }

    public List<CourseDTO> getByCourseListCreatedDateBetween(LocalDate date1, LocalDate date2) {
        List<CourseEntity> list = courseRepository.findByCreatedDateBetween(date1, date2);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this createdDate between: ");
        }
        return toDTO(list);
    }

    public Page<CourseDTO> paginationById(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<CourseEntity> page1 = courseRepository.findAll(pageable);
        Long totalCount = page1.getTotalElements();
        List<CourseEntity> entityList = page1.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<CourseDTO> response = new PageImpl<CourseDTO>(dtoList, pageable, totalCount);
        return response;
    }
    public Page<CourseDTO> paginationByCreatedDate(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<CourseEntity> page1 = courseRepository.findAll(pageable);
        Long totalCount = page1.getTotalElements();
        List<CourseEntity> entityList = page1.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<CourseDTO> response = new PageImpl<CourseDTO>(dtoList, pageable, totalCount);
        return response;

    }

    public Page<CourseDTO> pagingByPriceWithCreatedDate(Double price, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<CourseEntity> page1 = courseRepository.findAllByPrice(price, pageable);
        Long totalCount = page1.getTotalElements();
        List<CourseEntity> entityList = page1.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<CourseDTO> response = new PageImpl<CourseDTO>(dtoList, pageable, totalCount);
        return response;
    }

    public Page<CourseDTO> pagingByPricesWithCreateDateBetween(LocalDate date1, LocalDate date2, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<CourseEntity> page1 =  courseRepository.findByCreatedDateBetween(date1, date2, pageable);
        Long totalCount = page1.getTotalElements();
        List<CourseEntity> entityList = page1.getContent();
        List<CourseDTO> dtoList = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<CourseDTO> response = new PageImpl<CourseDTO>(dtoList, pageable, totalCount);
        return response;
    }

}
