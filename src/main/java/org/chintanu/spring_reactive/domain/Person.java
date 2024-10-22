package org.chintanu.spring_reactive.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Integer personId;
    private String firstName;
    private String lastName;
    private Integer age;
}
