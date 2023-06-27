package com.project.stundet.api.controller;

import com.project.stundet.api.dto.StudentDto;
import com.project.stundet.api.dto.StudentReportDto;
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
    ResponseEntity<StudentReportDto> allStudents() {
        return ResponseEntity.ok(studentService.fetchAllStudents());
    }

    @PostMapping
    ResponseEntity<Void> addStudent(@RequestBody List<StudentDto> studentDtos) {
        studentService.addStudents(studentDtos);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> editStudentData(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/count-all")
    Long studentsAmount() {
        return studentService.countAllStudents();
    }

    @GetMapping("/stats/count")
    Long studentsAmountForCourse(@RequestParam(name = "course", required = true) String course) {
        return studentService.countStudentsForCourse(course);
    }

    @GetMapping("/search-by-full-name")
    ResponseEntity<StudentReportDto> filterByStudentsData(@RequestParam(name = "name") String name, @RequestParam(name = "surname") String surname) {
        return ResponseEntity.ok(studentService.fetchStudentByFullName(name, surname));
    }

    @GetMapping("/search-by-course")
    ResponseEntity<StudentReportDto> filterByStudentsData(@RequestParam(name = "course", required = true) String course) {
        return ResponseEntity.ok(studentService.fetchStudentByCourse(course));
    }

    @GetMapping("/stats/avg-age")
    ResponseEntity<Double> countAvgAge() {
        return ResponseEntity.ok(studentService.countAvgAge());
    }
}
