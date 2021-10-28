package co.edu.icesi.ci.thymeval.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.repository.UserRepository;
import jdk.internal.jline.internal.Log;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserApp userApp = userRepository.findByUsername(username);

		if (userApp != null) {

			User.UserBuilder builder = User.withUsername(username).password(userApp.getPassword()).roles("");

			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}