package com.project.stundet.api.repository;

import com.project.stundet.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameLikeIgnoreCaseAndSurnameLikeIgnoreCase(String name, String surname);

    long countByCourse(String course);

}
