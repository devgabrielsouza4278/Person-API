package com.GADS.PersonAPI.Controller;

import com.GADS.PersonAPI.DTO.Request.PersonDTO;
import com.GADS.PersonAPI.DTO.Response.MessageResponseDTO;
import com.GADS.PersonAPI.Exception.PersonNotFoundException;
import com.GADS.PersonAPI.Services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updatePerson(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateByID(id, personDTO);
    }

    @GetMapping
    public List<PersonDTO> listALL() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findByID(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }


}
