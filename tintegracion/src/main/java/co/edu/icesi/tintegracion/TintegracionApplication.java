package co.edu.icesi.tintegracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.model.users.Usertypes;
import co.edu.icesi.tintegracion.services.implementations.PersonServiceImp;
import co.edu.icesi.tintegracion.services.implementations.UserServiceImp;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;
import co.edu.icesi.tintegracion.services.interfaces.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
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
		user1.setPassword("{noop}pass1");
		user1.setType(Usertypes.ADMINISTRATOR);

		
		userService.save(user1);

		UserSystem user2 = new UserSystem();
		user2.setUsername("andre123");
		user2.setPassword("{noop}pass2");
		user2.setType(Usertypes.OPERATOR);
		userService.save(user2);

		PersonService personService  = c.getBean(PersonServiceImp.class);


		Person person = new Person();

		person.setFirstname("Camilo");

		personService.save(person);


		Person person2 = new Person();

		person2.setFirstname("Andre :)");

		personService.save(person2);
	}

}
