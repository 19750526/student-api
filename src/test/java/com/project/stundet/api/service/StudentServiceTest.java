package com.project.stundet.api.service;

import com.project.stundet.api.dto.StudentReportDto;
import com.project.stundet.api.entity.Student;
import com.project.stundet.api.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;


    void setUp() {
        List<Student> students = new ArrayList<>();
        students.add(Student.builder().name("Bartek").surname("B").course("C").dateBirth(LocalDate.of(1995, 11, 10)).build());
        students.add(Student.builder().name("Kasia").surname("B").course("CE").dateBirth(LocalDate.of(1995, 12, 10)).build());
        students.add(Student.builder().name("Basia").surname("B").course("C").dateBirth(LocalDate.of(1993, 11, 10)).build());
        students.add(Student.builder().name("Asia").surname("B").course("CD").dateBirth(LocalDate.of(1992, 12, 10)).build());
        students.add(Student.builder().name("Damian").surname("E").course("F").dateBirth(LocalDate.of(1991, 11, 10)).build());
        studentRepository.saveAll(students);
    }

    //
    @Test
    void countAllStudents() {
        setUp();
        StudentReportDto report = studentService.fetchAllStudents();
        assertEquals(5, report.getResult().size());
    }

    @Test
    void avg() {
        List<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        double avg = studentService.avg(values);
        assertEquals(2.5, avg);
    }
}
