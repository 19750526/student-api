package com.project.stundet.api.service;

import com.project.stundet.api.entity.Student;
import com.project.stundet.api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    public Long countAllStudents() {
        return studentRepository.count();
    }


    public Long countStudentsForCourse(String course) {
        return studentRepository.countByCourse(course);
    }

}
