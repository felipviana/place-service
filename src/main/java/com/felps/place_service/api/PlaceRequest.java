package com.felps.place_service.api;

import java.time.LocalDateTime;

public record PlaceRequest
        (String name, String state) {
}
