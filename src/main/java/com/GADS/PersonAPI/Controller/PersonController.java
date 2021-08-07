package com.GADS.PersonAPI.Controller;

import com.GADS.PersonAPI.DTO.MessageResponseDTO;
import com.GADS.PersonAPI.Entity.Person;
import com.GADS.PersonAPI.Repository.PersonRepository;
import com.GADS.PersonAPI.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

        private PersonService personService;

        @Autowired
        public PersonController(PersonService personService) {
            this.personService = personService;
    }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public MessageResponseDTO createPerson (@RequestBody  Person person) {
            return personService.createPerson(person);
    }
}
