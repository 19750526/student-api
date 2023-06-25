package com.project.stundet.api.service;

import com.project.stundet.api.dto.StudentDto;
import com.project.stundet.api.entity.Student;
import com.project.stundet.api.error.NotFoundException;
import com.project.stundet.api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void addStudents(List<StudentDto> studentDtos) {
        List<Student> students = studentDtos.stream().map(studentDto -> {
            Student student = new Student();
            student.setName(studentDto.getName());
            student.setSurname(studentDto.getSurname());
            student.setCourse(studentDto.getCourse());
            student.setDateBirth(studentDto.getDateBirth());
            return student;
        }).collect(Collectors.toList());
        studentRepository.saveAll(students);
    }

    public void updateStudent(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Student with id %d not found", id)));
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setCourse(studentDto.getCourse());
        student.setDateBirth(studentDto.getDateBirth());
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
