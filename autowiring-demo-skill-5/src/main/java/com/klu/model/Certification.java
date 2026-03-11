package com.klu.model;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id = 201;
    private String name = "Full Stack";
    private String dateOfCompletion = "20-Jan-2026";

    @Override
    public String toString() {
        return "Certification [id=" + id +
                ", name=" + name +
                ", dateOfCompletion=" + dateOfCompletion + "]";
    }
}