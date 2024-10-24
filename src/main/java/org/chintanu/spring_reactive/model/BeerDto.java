package org.chintanu.spring_reactive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerDto {

    private Integer id;
    private String beerName;
    private String beerStyle;
    private String upc;
    private int quantityOnHand;
    private BigDecimal price;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
