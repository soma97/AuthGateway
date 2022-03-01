package com.example.demo.controller;

import com.example.demo.model.request.PersonRequest;
import com.example.demo.model.response.PersonResponse;
import com.example.demo.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.validation.Valid;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Iterable<PersonResponse> getPersons() {
        return personService.getAllPersons();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_REGULAR')")
    @GetMapping("/{id}")
    public PersonResponse getPerson(@PathVariable("id") Long id) {
        return personService.getPersonById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<PersonResponse> postPerson(@RequestBody @Valid PersonRequest personRequest) {
        PersonResponse personResponse = personService.createPerson(personRequest);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(personResponse.getId()).toUri()).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> putPerson(@PathVariable("id") Long id, @RequestBody @Valid PersonRequest personRequest) {
        return ResponseEntity.ok(personService.updatePerson(id, personRequest));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonResponse> deletePerson(@PathVariable(name = "id") long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
