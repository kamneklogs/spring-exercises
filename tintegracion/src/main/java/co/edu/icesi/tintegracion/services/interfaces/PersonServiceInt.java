package co.edu.icesi.tintegracion.services.interfaces;

import java.util.Optional;

import co.edu.icesi.tintegracion.model.person.Person;

public interface PersonServiceInt {
    public Optional<Person> findById(int i);

}
