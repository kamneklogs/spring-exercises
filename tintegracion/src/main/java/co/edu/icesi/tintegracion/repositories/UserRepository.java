package co.edu.icesi.tintegracion.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.model.users.Usertypes;

public interface UserRepository extends CrudRepository<UserSystem, String> {

    public Iterable<UserSystem> findByType(Usertypes type);

    public UserSystem findByUsername(String username);
}
