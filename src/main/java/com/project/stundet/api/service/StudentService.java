package com.project.stundet.api.service;

import com.project.stundet.api.dto.StudentDto;
import com.project.stundet.api.entity.Student;
import com.project.stundet.api.error.NotFoundException;
import com.project.stundet.api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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

    public List<StudentDto> fetchStudentByFullName(String name, String surname) {
        return studentRepository.findByNameLikeIgnoreCaseAndSurnameLikeIgnoreCase(name, surname).stream().map(student -> {
            return databaseToDto(student);
        }).collect(Collectors.toList());
    }

    private static StudentDto databaseToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setSurname(student.getSurname());
        studentDto.setCourse(student.getCourse());
        studentDto.setDateBirth(student.getDateBirth());
        return studentDto;
    }

    public List<StudentDto> fetchStudentByCourse(String course) {
        return studentRepository.findByCourse(course).stream().map(student -> databaseToDto(student))
                .collect(Collectors.toList());
    }

    public double countAvgAge() {
        List<Student> students = studentRepository.findAll();
        List<Integer> ages = new ArrayList<>();
        students.forEach(student -> {
            int age = Period.between(student.getDateBirth(), LocalDate.now()).getYears();
            ages.add(age);
        });
        double sum = 0;
        if(!ages.isEmpty()) {
            for (Integer age : ages) {
                sum += age;
            }
            return sum / ages.size();
        }
        return 0;
    }
}
