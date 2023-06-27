package com.project.stundet.api.service;

import com.project.stundet.api.dto.StatReportDto;
import com.project.stundet.api.dto.StudentDto;
import com.project.stundet.api.dto.StudentReportDto;
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

    public StudentReportDto fetchAllStudents() {
        List<StudentDto> students = studentRepository.findAll().stream().map(StudentService::databaseToDto).toList();
        return new StudentReportDto(students);
    }

    public StatReportDto countAllStudents() {
        return new StatReportDto(studentRepository.count());
    }

    public StatReportDto countStudentsForCourse(String course) {
        return new StatReportDto( studentRepository.countByCourse(course));
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

    public StudentReportDto fetchStudentByFullName(String name, String surname) {
        List<StudentDto> students = studentRepository.findByNameLikeIgnoreCaseAndSurnameLikeIgnoreCase(name, surname)
                .stream().map(StudentService::databaseToDto).toList();
        return new StudentReportDto(students);
    }

    private static StudentDto databaseToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setSurname(student.getSurname());
        studentDto.setCourse(student.getCourse());
        studentDto.setDateBirth(student.getDateBirth());
        return studentDto;
    }

    public StudentReportDto fetchStudentByCourse(String course) {
        var studentDtos = studentRepository.findByCourse(course).stream().map(StudentService::databaseToDto).toList();
        return new StudentReportDto(studentDtos);
    }

    public StatReportDto countAvgAge() {
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
            var avg = sum / ages.size();
            return new StatReportDto((long) avg);
        }
        return new StatReportDto(0L);
    }
}
