package org.chintanu.spring_reactive.repository;

import org.chintanu.spring_reactive.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }

    @Test
    @Disabled//Disabling as old test. Not relevant any more
    void findByIdMonoBlock() {

        Mono<Person> monoPerson = personRepository.findById(1);
        Person person = monoPerson.block();//Shouldn't be used as this defeats the purpose of non-blocking
        System.out.println(person);
    }

    @Test
    void findByIdMonoProper() {

        Mono<Person> monoPerson = personRepository.findById(1);
        monoPerson.subscribe(System.out::println);// Or Lambda can also be used as monoPerson.subscribe(el -> SOUT(el));
    }

    @Test
    void findByIdMonoWithMap() {

        Mono<Person> monoPerson = personRepository.findById(1);
        monoPerson.map(person -> person.getFirstName() + " " + person.getLastName())
                .subscribe(System.out::println);
        monoPerson.subscribe(System.out::println);// Same Mono can be utlised many times.
    }

    @Test
    void findByIdError() {

        Mono<Person> monoPerson = personRepository.findByIdError(1)
                .doOnError(ex -> System.out.println("Knock Knock : " + ex.getLocalizedMessage()));
        monoPerson.subscribe(System.out::println);
    }

    @Test
    void findByIdSingle() {

        Mono<Person> monoPerson = personRepository.findById(2);
        monoPerson.subscribe(System.out::println);
    }

    @Test
    void findByIdEmpty() {

        Mono<Person> monoPerson = personRepository.findById(9);
        monoPerson.subscribe(System.out::println);
    }

    @Test
    void findByIdException() {

        Mono<Person> monoPerson = personRepository.findById(1)
                .doOnError(ex -> System.out.println("Knock Knock : " + ex.getLocalizedMessage()));
        monoPerson.subscribe(System.out::println);// Or Lambda can also be used as monoPerson.subscribe(el -> SOUT(el));
    }

    @Test
    void findAllFluxBlock() {

        Flux<Person> fluxPerson = personRepository.findAll();
        Person person = fluxPerson.blockFirst();//Blocks & gets the 1st element. Shouldn't be used as this defeats the purpose of non-blocking
        System.out.println(person);
    }

    @Test
    void findAllFluxProper() {

        Flux<Person> fluxPerson = personRepository.findAll();
        fluxPerson.subscribe(System.out::println);
    }

    @Test
    void findAllFluxWithFilter() {

        Flux<Person> fluxPerson = personRepository.findAll();
        fluxPerson.filter(person -> person.getFirstName().equals("Tom"))
                .subscribe(System.out::println);
    }


    @Test
    void findAllFluxError() {

        Flux<Person> fluxPerson = personRepository.findAllError()
                .doOnError(ex -> System.out.println("Knock Knock : " + ex.getLocalizedMessage()));
        fluxPerson.subscribe(System.out::println);
    }
}