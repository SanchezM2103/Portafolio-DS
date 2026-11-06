package com.portafolio.service.impl;

import com.portafolio.entity.Course;
import com.portafolio.exception.ResourceNotFoundException;
import com.portafolio.repository.CourseRepository;
import com.portafolio.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso", id));
    }

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        Course existingCourse = findById(id);
        existingCourse.setName(course.getName());
        existingCourse.setOrganization(course.getOrganization());
        existingCourse.setDateCompletion(course.getDateCompletion());
        return courseRepository.save(existingCourse);
    }

    @Override
    public void delete(Long id) {
        courseRepository.delete(findById(id));
    }
}
