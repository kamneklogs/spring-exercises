package co.edu.icesi.tintegracion.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.repositories.PersonRepositoryInt;

@Service
public class PersonService {


    private PersonRepositoryInt personRepository;

    
    public PersonService(PersonRepositoryInt personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(int i) {
        return personRepository.findById(i);
    }



}
