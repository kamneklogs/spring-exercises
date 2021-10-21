package co.edu.icesi.ci.thymeval.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.model.UserGender;
import co.edu.icesi.ci.thymeval.model.UserType;
import co.edu.icesi.ci.thymeval.repository.UserRepository;
import co.edu.icesi.ci.thymeval.service.interfaces.UserService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(User user) {
		userRepository.save(user);
		return user;
	}

	public Optional<User> findById(long id) {

		return userRepository.findById(id);
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public Iterable<User> findAllPatients() {
		return userRepository.findByType(UserType.patient);
	}

	public Iterable<User> findAllDoctors() {
		return userRepository.findByType(UserType.doctor);
	}

	public void delete(User user) {
		userRepository.delete(user);

	}

	public UserGender[] getGenders() {

		return UserGender.values();
	}

	public UserType[] getTypes() {
		return UserType.values();
	}

	public void saveUserSecondValidation(User user) {

		log.info(user.getId()+"   SSSSSSSSSSSSSSSSSS");
		User entityUser = userRepository.findById(user.getId()).get();
		entityUser.setName(user.getName());
		entityUser.setGender(user.getGender());
		entityUser.setEmail(user.getEmail());
		entityUser.setType(user.getType());
		userRepository.save(entityUser);
	}
}
