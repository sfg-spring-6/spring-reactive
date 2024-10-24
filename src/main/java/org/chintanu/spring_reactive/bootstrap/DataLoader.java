package org.chintanu.spring_reactive.bootstrap;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.chintanu.spring_reactive.config.DatabaseConfig;
import org.chintanu.spring_reactive.domain.Beer;
import org.chintanu.spring_reactive.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//@Import(DatabaseConfig.class)
@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {

        loadData();
        beerRepository.count()
                .subscribe(System.out::println);
    }

    private void loadData() {

        beerRepository.count()
                .subscribe(count -> {
                    if (count == 0) {
                        Beer beer1 = Beer.builder()
                                .beerName("Galaxy Cat")
                                .beerStyle("Pale Ale")
                                .upc("12356")
                                .price(new BigDecimal("12.99"))
                                .quantityOnHand(122)
                                .createdDate(LocalDateTime.now())
                                .lastModifiedDate(LocalDateTime.now())
                                .build();

                        Beer beer2 = Beer.builder()
                                .beerName("Crank")
                                .beerStyle("Pale Ale")
                                .upc("12356222")
                                .price(new BigDecimal("11.99"))
                                .quantityOnHand(392)
                                .createdDate(LocalDateTime.now())
                                .lastModifiedDate(LocalDateTime.now())
                                .build();

                        Beer beer3 = Beer.builder()
                                .beerName("Sunshine City")
                                .beerStyle("IPA")
                                .upc("12356")
                                .price(new BigDecimal("13.99"))
                                .quantityOnHand(144)
                                .createdDate(LocalDateTime.now())
                                .lastModifiedDate(LocalDateTime.now())
                                .build();

                        beerRepository.save(beer1).subscribe();
                        beerRepository.save(beer2).subscribe();
                        beerRepository.save(beer3).subscribe();
                    }
                });
    }
}
