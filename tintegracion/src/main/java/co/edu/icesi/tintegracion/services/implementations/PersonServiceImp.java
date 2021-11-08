package co.edu.icesi.tintegracion.services.implementations;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.repositories.PersonRepositoryInt;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;

@Service
public class PersonServiceImp implements PersonService {

    private PersonRepositoryInt personRepository;

    public PersonServiceImp(PersonRepositoryInt personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(int i) {
        return personRepository.findById(i);
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person save(Person person) {

        person.setModifieddate(new Timestamp(System.currentTimeMillis()));
        return personRepository.save(person);
    }

}
