package com.example.demo.model.mapper;

import com.example.demo.model.entity.Person;
import com.example.demo.model.request.PersonRequest;
import com.example.demo.model.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    Person toModel(PersonRequest personRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", expression = "java( personRequest.getName() )")
    @Mapping(target = "surname", expression = "java( personRequest.getSurname() )")
    Person toModel(PersonRequest personRequest, long id);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    PersonResponse toView(Person person);

    List<PersonResponse> toViewList(List<Person> persons);
}
