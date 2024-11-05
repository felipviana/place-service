package com.felps.place_service.config;

import com.felps.place_service.domain.Place;
import com.felps.place_service.domain.PlaceRepository;
import com.felps.place_service.domain.PlaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaceConfig {

    @Bean
    PlaceService placeService(PlaceRepository placeRepository){
        return new PlaceService(placeRepository);
    }
}
