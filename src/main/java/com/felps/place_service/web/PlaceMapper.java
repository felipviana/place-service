package com.felps.place_service.web;

import com.felps.place_service.api.PlaceResponse;
import com.felps.place_service.domain.Place;

public class PlaceMapper {
    public static PlaceResponse fromPlaceToResponse(Place place){
        return new PlaceResponse(place.id(), place.name(), place.slug(), place.state(), place.createdAt(), place.updatedAt());
    }
}
