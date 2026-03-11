package com.klu.courseapi.controller;

import com.klu.courseapi.model.Course;
import com.klu.courseapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    //  Add Course
    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
    	service.addCourse(course);
        return new ResponseEntity<>("Course added successfully", HttpStatus.CREATED);
    }

    //  Get All Courses
    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        List<Course> list = service.getAllCourses();
        if (list.isEmpty())
            return new ResponseEntity<>("No courses found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //  Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id) {
        Course c = service.getCourseById(id);
        if (c == null)
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    //  Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id,
                                          @RequestBody Course course) {

        Course updated = service.updateCourse(id, course);
        if (updated == null)
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
    }

    //  Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        Course deleted = service.deleteCourse(id);
        if (deleted == null)
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }

    //  Search by title
    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchCourse(@PathVariable String title) {
        List<Course> result = service.searchByTitle(title);

        if (result.isEmpty())
            return new ResponseEntity<>("No matching courses", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
