package com.project.stundet.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class StudentReportDto extends BaseReport {
    private List<StudentDto> result;
}
