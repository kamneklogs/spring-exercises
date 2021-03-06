package co.edu.icesi.tintegracion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.repositories.UserRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserSystem userApp = userRepository.findById(username).get();

		if (userApp != null) {

			User.UserBuilder builder = User.withUsername(username).password(userApp.getPassword())
					.roles(userApp.getType().toString());

			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}