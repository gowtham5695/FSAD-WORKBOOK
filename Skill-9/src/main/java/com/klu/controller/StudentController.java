package com.klu.controller;

import org.springframework.web.bind.annotation.*;
import com.klu.exception.StudentNotFoundException;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public String getStudent(@PathVariable int id) {

        if (id <= 0) {
            throw new StudentNotFoundException("Student ID is invalid or not found");
        }

        return "Student Details for ID: " + id;
    }
}