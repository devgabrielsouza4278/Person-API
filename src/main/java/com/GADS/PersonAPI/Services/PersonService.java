package com.GADS.PersonAPI.Services;

import com.GADS.PersonAPI.DTO.Request.PersonDTO;
import com.GADS.PersonAPI.DTO.Response.MessageResponseDTO;
import com.GADS.PersonAPI.Entity.Person;
import com.GADS.PersonAPI.Exception.PersonNotFoundException;
import com.GADS.PersonAPI.Mapper.PersonMapper;
import com.GADS.PersonAPI.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson (PersonDTO personDTO) {
            Person personToSave= personMapper.toModel(personDTO);

            Person savedPerson = personRepository.save(personToSave);
            return MessageResponseDTO
                    .builder()
                    .Message("Pessoa cadastrada com o id: " + savedPerson.getId())
                    .build();

    }

    public List<PersonDTO> listAll (){
        List<Person> allPeople = personRepository.findAll();

        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById ( Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(
                        ()-> new PersonNotFoundException(id));

        return personMapper.toDTO(person);

    }
}
