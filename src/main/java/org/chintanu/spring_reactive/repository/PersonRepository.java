package org.chintanu.spring_reactive.repository;

import org.chintanu.spring_reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {

    public Mono<Person> findById(Integer id);
    public Mono<Person> findByIdError(Integer id);
    public Flux<Person> findAll();
    public Flux<Person> findAllError();
}
