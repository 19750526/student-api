package com.project.stundet.api.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class BaseReport {
    private LocalDate reportDate = LocalDate.now();
}
