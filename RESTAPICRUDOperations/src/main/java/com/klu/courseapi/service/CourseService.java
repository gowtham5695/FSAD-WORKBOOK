package com.klu.courseapi.service;

import com.klu.courseapi.model.Course;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    private Map<Integer, Course> courseMap = new HashMap<>();

    // ADD
    public Course addCourse(Course course) {
        courseMap.put(course.getCourseId(), course);
        return course;
    }

    // VIEW ALL
    public List<Course> getAllCourses() {
        return new ArrayList<>(courseMap.values());
    }

    // VIEW BY ID
    public Course getCourseById(int id) {
        return courseMap.get(id);
    }

    // UPDATE
    public Course updateCourse(int id, Course newCourse) {
        if (courseMap.containsKey(id)) {
            newCourse.setCourseId(id);
            courseMap.put(id, newCourse);
            return newCourse;
        }
        return null;
    }

    // DELETE
    public Course deleteCourse(int id) {
        return courseMap.remove(id);
    }

    // SEARCH BY TITLE
    public List<Course> searchByTitle(String title) {
        List<Course> result = new ArrayList<>();
        for (Course c : courseMap.values()) {
            if (c.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }
}
