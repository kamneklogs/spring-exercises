package co.edu.icesi.tintegracion.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.tintegracion.model.person.Businessentity;

public interface BussinesEntityRepositoryInt extends CrudRepository<Businessentity, Integer>{
    
}
