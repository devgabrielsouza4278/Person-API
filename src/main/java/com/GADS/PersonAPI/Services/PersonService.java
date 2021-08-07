package com.GADS.PersonAPI.Services;

import com.GADS.PersonAPI.DTO.MessageResponseDTO;
import com.GADS.PersonAPI.Entity.Person;
import com.GADS.PersonAPI.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson (Person person) {
        Person savedPerson = personRepository.save(person);
            return MessageResponseDTO
                    .builder()
                    .Message("Pessoa cadastrada com o id: " + savedPerson.getId())
                    .build();

    }
}
