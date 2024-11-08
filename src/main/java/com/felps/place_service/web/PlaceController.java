package com.felps.place_service.web;

import com.felps.place_service.api.PlaceRequest;
import com.felps.place_service.api.PlaceResponse;
import com.felps.place_service.domain.PlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
public class PlaceController {


    @Autowired
    private PlaceService placeService;


    @GetMapping
    public ResponseEntity<Flux<PlaceResponse>> findAll(){
        Flux<PlaceResponse> places = placeService.list().map(PlaceMapper::fromPlaceToResponse);
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PlaceResponse>> findById(@PathVariable Long id){
        return placeService.findById(id)
                .map(PlaceMapper::fromPlaceToResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
    @PostMapping
    public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest request){
        var placeResponse = placeService.create(request).map(PlaceMapper::fromPlaceToResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<PlaceResponse>> update(@PathVariable Long id,@Valid  @RequestBody PlaceRequest request){
        var placeResponse = placeService.update(id, request).map(PlaceMapper::fromPlaceToResponse);
        return ResponseEntity.status(HttpStatus.OK).body(placeResponse);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable Long id){
        return placeService.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ResponseStatusException.class, e -> Mono.just(ResponseEntity.notFound().build()));
    }
}