package co.edu.icesi.tintegracion.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.repositories.PersonRepositoryInt;
import co.edu.icesi.tintegracion.services.interfaces.PersonServiceInt;

@Service
public class PersonService implements PersonServiceInt {

    private PersonRepositoryInt personRepository;

    public PersonService(PersonRepositoryInt personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(int i) {
        return personRepository.findById(i);
    }

}
