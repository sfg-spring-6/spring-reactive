package org.chintanu.spring_reactive.controller;

import org.chintanu.spring_reactive.model.BeerDto;
import org.chintanu.spring_reactive.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class BeerController {

    public static final String PATH = "/api/v2/beer";
    public static final String PATH_WITH_ID = "/api/v2/beer/{beerId}";

    @Autowired
    BeerService beerService;

    @GetMapping(PATH)
    public Flux<BeerDto> listBeers() {
         return beerService.listBeers();
    }

    @GetMapping(PATH_WITH_ID)
    public Mono<BeerDto> getBeerById(@PathVariable("beerId") Integer beerId) {

        return beerService.getBeerById(beerId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping(PATH)
    public Mono<ResponseEntity<Void>> createBeer(@RequestBody BeerDto beerDto) {
        return beerService.createBeer(beerDto)
                .map(beerDto1 -> ResponseEntity.created(UriComponentsBuilder.fromHttpUrl("http://localhost:8080" + PATH)
                                                                            .path("/{beerId}")
                                                                            .buildAndExpand(Map.of("beerId", beerDto1.getId()))
                                                                            .toUri())
                                                .build());
    }

    @DeleteMapping(PATH_WITH_ID)
    public Mono<ResponseEntity<Void>> deleteBeerById(@PathVariable("beerId") Integer beerId) {
        return beerService.getBeerById(beerId)
                        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                        .map(beerDto -> beerService.deleteBeerById(beerId))
                        .thenReturn(ResponseEntity.noContent().build());
        //Here if we use the map() method in-place of thenReturn then it would always send 200 OK as the map
        //wouldn't run because the response from the service is a Mono<Void>, so the map method won't run for Void as it contains nothing
    }
}
