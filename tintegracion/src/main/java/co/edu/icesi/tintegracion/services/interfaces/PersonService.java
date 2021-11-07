package co.edu.icesi.tintegracion.services.interfaces;

import java.util.Optional;

import co.edu.icesi.tintegracion.model.person.Person;

public interface PersonService {
    public Optional<Person> findById(int i);

}
