package co.edu.icesi.tintegracion.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.model.users.UserType;
import co.edu.icesi.tintegracion.repositories.UserRepository;
import co.edu.icesi.tintegracion.services.interfaces.UserService;

@Service
public class UserServiceImp implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserSystem save(UserSystem user) {
		userRepository.save(user);
		return user;
	}

	public Optional<UserSystem> findByUsername(String username) {

		return userRepository.findById(username);
	}

	public Iterable<UserSystem> findAll() {
		return userRepository.findAll();
	}

	public Iterable<UserSystem> findAllAdministrators() {
		return userRepository.findByType(UserType.ADMINISTRATOR);
	}

	public Iterable<UserSystem> findAllOperators() {
		return userRepository.findByType(UserType.OPERATOR);
	}

	public void delete(UserSystem user) {
		userRepository.delete(user);

	}

	public UserType[] getTypes() {
		return UserType.values();
	}
}
