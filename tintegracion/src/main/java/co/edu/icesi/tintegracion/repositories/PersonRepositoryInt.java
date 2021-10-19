package co.edu.icesi.tintegracion.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.tintegracion.model.person.Person;

public interface PersonRepositoryInt extends CrudRepository<Person, Integer>{
    
}
