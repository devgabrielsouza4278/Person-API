package com.GADS.PersonAPI.Services;

import com.GADS.PersonAPI.DTO.Request.PersonDTO;
import com.GADS.PersonAPI.DTO.Response.MessageResponseDTO;
import com.GADS.PersonAPI.Entity.Person;
import com.GADS.PersonAPI.Exception.PersonNotFoundException;
import com.GADS.PersonAPI.Mapper.PersonMapper;
import com.GADS.PersonAPI.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Pessoa cadastrada com o id: ");

    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();

        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);

    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);

    }


    public MessageResponseDTO updateByID(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Atualizado o cadastro do usuário: ");

    }



    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .Message(message + id)
                .build();
    }
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(
                        () -> new PersonNotFoundException(id));


    }

}
