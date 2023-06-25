package com.project.stundet.api.error;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
