package com.example.demo.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PersonResponse {
    private long id;
    private String name;
    private String surname;
}
