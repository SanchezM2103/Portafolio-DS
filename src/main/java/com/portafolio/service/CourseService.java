package com.portafolio.service;

import com.portafolio.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(Long id);

    Course create(Course course);

    Course update(Long id, Course course);

    void delete(Long id);
}
