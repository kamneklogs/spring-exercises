package co.edu.icesi.ci.thymeval.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.model.UserType;

public interface UserRepository extends CrudRepository<UserApp, Long> {

	List<UserApp> findByName(String name);

	UserApp findByUsername(String userName);

	List<UserApp> findByType(UserType patient);

}
