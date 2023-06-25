package com.project.stundet.api.controller;

import com.project.stundet.api.dto.StudentDto;
import com.project.stundet.api.entity.Student;
import com.project.stundet.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    @GetMapping
    List<Student> all() {
        return studentService.fetchAllStudents();
    }

    @PostMapping
    ResponseEntity<Void> addStudent(List<StudentDto> studentDtos) {
        studentService.addStudents(studentDtos);
        return ResponseEntity.ok().build();
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
