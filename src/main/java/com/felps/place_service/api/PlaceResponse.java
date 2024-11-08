package com.felps.place_service.api;

import java.time.LocalDateTime;

public record PlaceResponse
        (Long Id, String name, String slug, String state, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
