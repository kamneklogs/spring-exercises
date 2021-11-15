package co.edu.icesi.tintegracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.tintegracion.model.hr.Department;
import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.model.users.Usertypes;
import co.edu.icesi.tintegracion.services.implementations.DepartmentServiceImp;
import co.edu.icesi.tintegracion.services.implementations.EmployeeServiceImp;
import co.edu.icesi.tintegracion.services.implementations.PersonServiceImp;
import co.edu.icesi.tintegracion.services.implementations.UserServiceImp;
import co.edu.icesi.tintegracion.services.interfaces.DepartmentService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;
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

		EmployeeService employeeService = c.getBean(EmployeeServiceImp.class);

		DepartmentService departmentService = c.getBean(DepartmentServiceImp.class);

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

		PersonService personService = c.getBean(PersonServiceImp.class);

		Person person = new Person();

		person.setFirstname("Camilo");
		person.setLastname("Escobar");
		person.setTitle("Developer");

		personService.save(person);

		Person person2 = new Person();

		person2.setFirstname("Andre :)");
		person2.setLastname("Rodriguez");
		person2.setTitle("Product owner");

		personService.save(person2);

		Employee employee1 = new Employee();

		employee1.setBusinessentityid(person.getBusinessentityid());
		employeeService.save(employee1);



		Employee employee2 = new Employee();

		employee2.setBusinessentityid(person2.getBusinessentityid());
		employeeService.save(employee2);


		Department department = new Department();

		department.setName("Dep1");

		departmentService.save(department);


		Department department2 = new Department();

		department2.setName("Dep2");

		departmentService.save(department2);
	}

}
