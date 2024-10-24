package org.chintanu.spring_reactive.service;

import org.chintanu.spring_reactive.model.BeerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {

    Flux<BeerDto> listBeers();
    Mono<BeerDto> getBeerById(Integer beerId);
    Mono<BeerDto> createBeer(BeerDto beerDto);
    Mono<Void> deleteBeerById(Integer beerId);
}
