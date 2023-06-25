package com.project.stundet.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentApi {

    @GetMapping("test")
    List<String> test() {
        return new ArrayList<>();
    }
}