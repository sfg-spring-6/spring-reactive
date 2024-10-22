package org.chintanu.spring_reactive.repository;

import org.chintanu.spring_reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person john, jane, tom;

    public PersonRepositoryImpl() {

        john = Person.builder().personId(1).age(20).firstName("John").lastName("Doe").build();
        //john = Person.builder().personId(1).age(20).firstName("John").lastName("Doe").build();
        jane = Person.builder().personId(2).age(28).firstName("Jane").lastName("Smith").build();
        tom = Person.builder().personId(3).age(54).firstName("Tom").lastName("Cruise").build();
    }

    @Override
    public Mono<Person> findById(Integer id) {

        Flux<Person> flux = findAll();

        Mono<Person> mono = flux.filter(e -> e.getPersonId().equals(id))
                .singleOrEmpty();

        return mono;
    }

    @Override
    public Mono<Person> findByIdError(Integer id) {

        try {
            int i = 5 / 0;
        } catch (Exception e) {
            return Mono.error(e);
        }
        return Mono.just(jane);

    }

    @Override
    public Flux<Person> findAll() {

        return Flux.just(john, jane, tom, john);
    }

    @Override
    public Flux<Person> findAllError() {

        try {
            int i = 5 / 0;
        } catch (Exception e) {
            return Flux.error(e);
        }
        return Flux.just(john, jane, tom);
    }
}
