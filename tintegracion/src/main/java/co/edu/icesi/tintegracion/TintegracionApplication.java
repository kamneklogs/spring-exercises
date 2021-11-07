package co.edu.icesi.tintegracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.model.users.UserType;
import co.edu.icesi.tintegracion.services.implementations.UserServiceImp;
import co.edu.icesi.tintegracion.services.interfaces.UserService;

@SpringBootApplication
public class TintegracionApplication {
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext c = SpringApplication.run(TintegracionApplication.class, args);

		UserService userService = c.getBean(UserServiceImp.class);

		UserSystem user1 = new UserSystem();
		user1.setUsername("charles777");
		user1.setPassword("{noop}password1");
		user1.setType(UserType.ADMINISTRATOR);
		userService.save(user1);

		UserSystem user2 = new UserSystem();
		user1.setUsername("andre123");
		user1.setPassword("password2");
		user1.setType(UserType.OPERATOR);
		userService.save(user1);
	}

}
