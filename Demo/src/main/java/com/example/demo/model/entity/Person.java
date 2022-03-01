package com.example.demo.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;

    public Person(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}