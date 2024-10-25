package org.chintanu.spring_reactive.controller;

import org.chintanu.spring_reactive.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureWebTestClient
class BeerControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void listBeers() throws Exception {

        webTestClient.get()
                .uri(BeerController.PATH)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    void getBeerById() throws Exception {

        webTestClient.get()
                .uri(BeerController.PATH_WITH_ID, 3)
                .exchange()
                .expectStatus().isOk()
                //.expectBody(BeerDto.class)
                .expectBody().jsonPath("$.beerName").isEqualTo("Sunshine City");
    }

    @Test
    void getBeerByIdNotFound() throws Exception {

        webTestClient.get()
                .uri(BeerController.PATH_WITH_ID, 9898)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void createBeer() throws Exception {

        BeerDto beerDto = BeerDto.builder().beerName("Magnum").beerStyle("Bud").price(new BigDecimal("5")).quantityOnHand(34).build();

        webTestClient.post()
                .uri(BeerController.PATH)
                .bodyValue(beerDto)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("Location")
                .expectBody().isEmpty();
    }

    @Test
    void deleteBeerById() throws Exception {

        webTestClient.delete()
                .uri(BeerController.PATH_WITH_ID, 3)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void deleteBeerByIdNotFound() throws Exception {

        webTestClient.delete()
                .uri(BeerController.PATH_WITH_ID, 9898)
                .exchange()
                .expectStatus().isNotFound();
    }
}