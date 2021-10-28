package co.edu.icesi.ci.thymeval.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.thymeval.model.UserApp;
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

	public UserApp save(UserApp user) {
		userRepository.save(user);
		return user;
	}

	public Optional<UserApp> findById(long id) {

		return userRepository.findById(id);
	}

	public Iterable<UserApp> findAll() {
		return userRepository.findAll();
	}

	public Iterable<UserApp> findAllPatients() {
		return userRepository.findByType(UserType.patient);
	}

	public Iterable<UserApp> findAllDoctors() {
		return userRepository.findByType(UserType.doctor);
	}

	public void delete(UserApp user) {
		userRepository.delete(user);

	}

	public UserGender[] getGenders() {

		return UserGender.values();
	}

	public UserType[] getTypes() {
		return UserType.values();
	}

	public void saveUserSecondValidation(UserApp user) {

		log.info(user.getId()+"   SSSSSSSSSSSSSSSSSS");
		UserApp entityUser = userRepository.findById(user.getId()).get();
		entityUser.setName(user.getName());
		entityUser.setGender(user.getGender());
		entityUser.setEmail(user.getEmail());
		entityUser.setType(user.getType());
		userRepository.save(entityUser);
	}
}
