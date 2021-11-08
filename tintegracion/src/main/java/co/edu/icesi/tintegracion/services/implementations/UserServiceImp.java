package co.edu.icesi.tintegracion.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.model.users.Usertypes;
import co.edu.icesi.tintegracion.repositories.UserRepository;
import co.edu.icesi.tintegracion.services.interfaces.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
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
		return userRepository.findByType(Usertypes.ADMINISTRATOR);
	}

	public Iterable<UserSystem> findAllOperators() {
		return userRepository.findByType(Usertypes.OPERATOR);
	}

	public void delete(UserSystem user) {
		userRepository.delete(user);

	}

	public Usertypes[] getTypes() {
		return Usertypes.values();
	}
}
