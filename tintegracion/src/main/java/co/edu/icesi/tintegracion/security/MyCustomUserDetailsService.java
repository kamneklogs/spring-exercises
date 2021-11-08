package co.edu.icesi.tintegracion.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info(username + "XXXXXXXXXXXXXXXXXXXXXXXXX");
		UserSystem userApp = userRepository.findById(username).get();

		log.info(userApp.getUsername() + "YYYYYYYYYYYYYYYYYYYYYYYYYYY");
		if (userApp != null) {

			User.UserBuilder builder = User.withUsername(username).password(userApp.getPassword())
					.roles(userApp.getType().toString());
			log.info(userApp.getPassword() + "YYYYYYYYYYYYYYYYYYYYYYYYYYY");

			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}