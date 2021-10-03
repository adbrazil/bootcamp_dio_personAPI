package one.digitalinnovation.personapi.service;


import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PersonService {


    private PersonRepository personRepository;
    private final  PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }

    public MessageResponseDTO createPerson( PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Create a person with ID:");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople= this.personRepository.findAll();
        return allPeople
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExist(id);
        return personMapper.toDTO(person);
    }


    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExist(id);
        this.personRepository.deleteById(id);
    }



    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExist(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Update a person with ID:");

    }

    private Person verifyIfExist(Long id) throws PersonNotFoundException {
        return this.personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }
}
