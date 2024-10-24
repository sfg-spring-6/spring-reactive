package org.chintanu.spring_reactive.service;

import org.chintanu.spring_reactive.mapper.BeerMapper;
import org.chintanu.spring_reactive.model.BeerDto;
import org.chintanu.spring_reactive.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BeerServiceImpl implements BeerService {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Override
    public Flux<BeerDto> listBeers() {

        return beerRepository.findAll()
                .map(beerMapper::convertBeerToBeerDto);
    }

    @Override
    public Mono<BeerDto> getBeerById(Integer beerId) {

        return beerRepository.findById(beerId)
                .map(beerMapper::convertBeerToBeerDto);
    }

    @Override
    public Mono<BeerDto> createBeer(BeerDto beerDto) {

        return beerRepository.save(beerMapper.convertBeerDtoToBeer(beerDto))
                .map(beerMapper::convertBeerToBeerDto);
    }

    @Override
    public Mono<Void> deleteBeerById(Integer beerId) {

        return beerRepository.deleteById(beerId);
    }
}
