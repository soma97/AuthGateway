package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.entity.Person;
import com.example.demo.model.mapper.PersonMapper;
import com.example.demo.model.request.PersonRequest;
import com.example.demo.model.response.PersonResponse;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonResponse> getAllPersons() {
        return personMapper.toViewList(personRepository.findAll());
    }

    public PersonResponse getPersonById(Long id) {
        return personRepository.findById(id)
                                .map(personMapper::toView)
                                .orElseThrow(() -> new NotFoundException(Person.class, id));
    }

    public PersonResponse createPerson(PersonRequest personRequest) {
        return personMapper.toView(personRepository.save(personMapper.toModel(personRequest)));
    }

    public PersonResponse updatePerson(long id, PersonRequest personRequest) {
        return personRepository.findById(id)
                                .map(value -> personMapper.toView(personRepository.save(personMapper.toModel(personRequest, id))))
                                .orElseThrow(() -> new NotFoundException(Person.class, id));
    }

    public void deletePerson(long id) {
        if(!personRepository.existsById(id)) {
            throw new NotFoundException(Person.class, id);
        }
        personRepository.deleteById(id);
    }
}
