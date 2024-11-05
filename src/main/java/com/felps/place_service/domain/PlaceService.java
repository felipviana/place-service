package com.felps.place_service.domain;

import com.felps.place_service.api.PlaceRequest;
import com.github.slugify.Slugify;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

public class PlaceService {
    private PlaceRepository placeRepository;
    private Slugify slg;

    public PlaceService(PlaceRepository placeRepository){
        this.placeRepository=placeRepository;
        this.slg = Slugify.builder().build();
    }
    public Mono<Place> findById(Long id){
        return placeRepository.findById(id);
    }

    public Flux<Place> list(){
        return placeRepository.findAll()
                .sort(Comparator.comparing(Place::name));
    }

    public Mono<Place> create(PlaceRequest placeRequest){
        var place = new Place(null, placeRequest.name(), slg.slugify(placeRequest.name()), placeRequest.state(), null,null);
        return placeRepository.save(place);
    }

    public Mono<Place> update(PlaceRequest placeRequest){
        var place = new Place(null, placeRequest.name(), slg.slugify(placeRequest.name()), placeRequest.state(), null, null);
        return placeRepository.save(place);
    }

    public Mono<Void> delete(Long id){
        return placeRepository.deleteById(id)
                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found")));
    }

}
