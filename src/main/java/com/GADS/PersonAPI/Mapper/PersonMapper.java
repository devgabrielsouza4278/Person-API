package com.GADS.PersonAPI.Mapper;

import com.GADS.PersonAPI.DTO.Request.PersonDTO;
import com.GADS.PersonAPI.Entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel (PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
