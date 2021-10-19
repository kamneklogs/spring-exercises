package co.edu.icesi.ci.thymeval.service.interfaces;

import java.util.Optional;

import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.model.UserGender;
import co.edu.icesi.ci.thymeval.model.UserType;

public interface UserService {
	public void delete(User user);

	public Iterable<User> findAll();

	public Iterable<User> findAllDoctors();

	public Iterable<User> findAllPatients();

	public Optional<User> findById(long id);

	public UserGender[] getGenders();

	public UserType[] getTypes();

	public void save(User user);

	public void update(User user);
}
