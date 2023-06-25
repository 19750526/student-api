package com.project.stundet.api.controller;

import com.project.stundet.api.entity.Student;
import com.project.stundet.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;
    @GetMapping("test")
    List<String> test() {
        return new ArrayList<>();
    }

    @GetMapping
    List<Student> all() {
        return studentService.fetchAllStudents();
    }

    @GetMapping("/stats/count-all")
    Long studentsAmount() {
        return studentService.countAllStudents();
    }

    @GetMapping("/stats/count")
    Long studentsAmountForCourse(@RequestParam(name = "course", required = true) String course) {
        return studentService.countStudentsForCourse(course);
    }
}
