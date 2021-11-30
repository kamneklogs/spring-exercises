package co.edu.icesi.tintegracion.services.interfaces;

import co.edu.icesi.tintegracion.model.person.Person;

public interface PersonService {
    public Person findById(int i);

    public Iterable<Person> findAll();

    public Person save(Person person);

    public Person update(Person person);

}
