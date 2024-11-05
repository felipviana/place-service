package com.felps.place_service.domain;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Place(
        Long id, String name, String slug, String state, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
