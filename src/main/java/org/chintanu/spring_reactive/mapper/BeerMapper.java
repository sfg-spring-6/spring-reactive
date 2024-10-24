package org.chintanu.spring_reactive.mapper;

import org.chintanu.spring_reactive.domain.Beer;
import org.chintanu.spring_reactive.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    public BeerDto convertBeerToBeerDto(Beer beer);
    public Beer convertBeerDtoToBeer(BeerDto beerDto);
}
