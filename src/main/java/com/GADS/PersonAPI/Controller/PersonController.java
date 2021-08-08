package com.GADS.PersonAPI.Controller;

import com.GADS.PersonAPI.DTO.Request.PersonDTO;
import com.GADS.PersonAPI.DTO.Response.MessageResponseDTO;
import com.GADS.PersonAPI.Entity.Person;
import com.GADS.PersonAPI.Exception.PersonNotFoundException;
import com.GADS.PersonAPI.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        public MessageResponseDTO createPerson (@RequestBody @Valid  PersonDTO personDTO) {
            return personService.createPerson(personDTO);
    }

        @GetMapping
        public List<PersonDTO> listALL () {
            return personService.listAll();
        }

        @GetMapping("/{id}")
        public PersonDTO findByID(@PathVariable  Long id) throws PersonNotFoundException {
            return personService.findById(id);
        }


}
