package co.edu.icesi.tintegracion;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.tintegracion.model.hr.Department;
import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;
import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.model.users.Usertypes;
import co.edu.icesi.tintegracion.services.implementations.DepartmentServiceImp;
import co.edu.icesi.tintegracion.services.implementations.EmployeeDepartmentHistoryServiceImp;
import co.edu.icesi.tintegracion.services.implementations.EmployeeServiceImp;
import co.edu.icesi.tintegracion.services.implementations.EmployeepayhistoryServiceImp;
import co.edu.icesi.tintegracion.services.implementations.PersonServiceImp;
import co.edu.icesi.tintegracion.services.implementations.UserServiceImp;
import co.edu.icesi.tintegracion.services.interfaces.DepartmentService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeDepartmentHistoryService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeePayHistoryService;
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

		// ***************ENTITIES FOR TESTING **********************

		UserService userService = c.getBean(UserServiceImp.class);

		PersonService personService = c.getBean(PersonServiceImp.class);

		EmployeeService employeeService = c.getBean(EmployeeServiceImp.class);

		DepartmentService departmentService = c.getBean(DepartmentServiceImp.class);

		EmployeeDepartmentHistoryService employeeDepartmentHistoryService = c
				.getBean(EmployeeDepartmentHistoryServiceImp.class);

		EmployeePayHistoryService employeePayHistoryService = c.getBean(EmployeepayhistoryServiceImp.class);

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

		Person person = new Person();

		person.setFirstname("Camilo");
		person.setLastname("Escobar");
		person.setTitle("Developer");

		personService.save(person);

		Person person2 = new Person();

		person2.setFirstname("Andrea");
		person2.setLastname("Rodriguez");
		person2.setTitle("Software engineering");

		personService.save(person2);

		Person person3 = new Person();

		person3.setFirstname("Cristhian");
		person3.setLastname("Cordoba");
		person3.setTitle("Data scientist");

		personService.save(person3);

		Person person4 = new Person();

		person4.setFirstname("Anderson");
		person4.setLastname("Rueda");
		person4.setTitle("Lawyer");

		personService.save(person4);

		Employee employee1 = new Employee();

		employee1.setBusinessentityid(person.getBusinessentityid());
		employee1.setJobtitle("Backend developer");
		employee1.setBirthdate(new Date());

		employeeService.save(employee1);

		Employee employee2 = new Employee();

		employee2.setBusinessentityid(person2.getBusinessentityid());
		employee2.setJobtitle("Project manager");
		employee2.setBirthdate(new Date());
		employeeService.save(employee2);

		Employee employee3 = new Employee();

		employee3.setBusinessentityid(person3.getBusinessentityid());
		employee3.setJobtitle("Project owner");
		employee3.setBirthdate(new Date());
		employeeService.save(employee3);

		Department department = new Department();

		department.setName("QA");
		department.setGroupname("TI / Services");

		departmentService.save(department);

		Department department2 = new Department();

		department2.setName("Research and Development");
		department2.setGroupname("TI / Services");

		departmentService.save(department2);

		Employeedepartmenthistory dH1 = new Employeedepartmenthistory();

		dH1.setBusinessentityid(employee1.getBusinessentityid());

		dH1.setStartdate(new Date());
		dH1.setEnddate(new Date());

		employeeDepartmentHistoryService.save(dH1);

		Employeedepartmenthistory dH2 = new Employeedepartmenthistory();

		dH2.setBusinessentityid(employee3.getBusinessentityid());

		dH2.setStartdate(new Date());
		dH2.setEnddate(new Date());

		employeeDepartmentHistoryService.save(dH2);

		Employeepayhistory payHistory1 = new Employeepayhistory();
		payHistory1.setEmployee(employee1);
		payHistory1.setPayfrequency(15);
		payHistory1.setRate(new BigDecimal(1500));

		employeePayHistoryService.save(payHistory1, employee1.getBusinessentityid());

		Employeepayhistory payHistory2 = new Employeepayhistory();
		payHistory2.setEmployee(employee3);
		payHistory2.setPayfrequency(30);
		payHistory2.setRate(new BigDecimal(2840));

		employeePayHistoryService.save(payHistory2, employee3.getBusinessentityid());

	}

}
