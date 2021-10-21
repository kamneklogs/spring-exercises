package co.edu.icesi.ci.thymeval.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.model.UserGender;
import co.edu.icesi.ci.thymeval.model.UserType;
import co.edu.icesi.ci.thymeval.repository.UserRepository;
import co.edu.icesi.ci.thymeval.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);

	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Iterable<User> findAllDoctors() {
		return userRepository.findByType(UserType.doctor);
	}

	@Override
	public Iterable<User> findAllPatients() {
		return userRepository.findByType(UserType.patient);
	}

	@Override
	public Optional<User> findById(long id) {

		return userRepository.findById(id);
	}

	@Override
	public UserGender[] getGenders() {

		return UserGender.values();
	}

	@Override
	public UserType[] getTypes() {
		return UserType.values();
	}

	@Override
	public void save(User user) {
		userRepository.save(user);

	}

	@Override
	@Transactional
	public void update(User user) {
		User entityUser = userRepository.findById(user.getId()).get();
		entityUser.setBirthDate(user.getBirthDate());
		entityUser.setEmail(user.getEmail());
		entityUser.setGender(user.getGender());
		entityUser.setName(user.getName());
		entityUser.setType(user.getType());
		userRepository.save(entityUser);

	}
}
