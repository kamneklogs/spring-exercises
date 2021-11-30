package co.edu.icesi.tintegracion.services.implementations;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.dao.IPersonDao;
import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;

@Service
public class PersonServiceImp implements PersonService {

    private IPersonDao personRepository;

    public PersonServiceImp(IPersonDao personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(int i) {
        return personRepository.findById(i);
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person save(Person person) {

        person.setModifieddate(new Timestamp(System.currentTimeMillis()));
        personRepository.save(person);

        return personRepository.findById(person.getBusinessentityid());
    }

    @Override
    public Person update(Person person) {

        personRepository.update(person);

        return personRepository.findById(person.getBusinessentityid());
    }

}
