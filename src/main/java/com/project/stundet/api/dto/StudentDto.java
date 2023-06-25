package com.project.stundet.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StudentDto {
    private String name;
    private String surname;
    private LocalDate dateBirth;
    private String course;
}
