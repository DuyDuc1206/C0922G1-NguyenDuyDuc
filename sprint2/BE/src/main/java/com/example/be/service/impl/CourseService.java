package com.example.be.service.impl;

import com.example.be.model.Course;
import com.example.be.repository.ICourseRepository;
import com.example.be.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<Course> findCourse(String name) {
        return courseRepository.findCourseByCourseName(name);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}