package co.edu.icesi.tintegracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.model.hr.EmployeepayhistoryPK;
import co.edu.icesi.tintegracion.model.person.Businessentity;
import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.repositories.BussinesEntityRepositoryInt;
import co.edu.icesi.tintegracion.repositories.DepartmentRepositoryInt;
import co.edu.icesi.tintegracion.repositories.EmployeeDepartmentHistoryRepositoryInt;
import co.edu.icesi.tintegracion.repositories.EmployeePayHistoryRepositoryInt;
import co.edu.icesi.tintegracion.repositories.EmployeeRepositoryInt;
import co.edu.icesi.tintegracion.repositories.PersonRepositoryInt;
import co.edu.icesi.tintegracion.repositories.ShiftRepositoryInt;
import co.edu.icesi.tintegracion.services.EmployeeService;
import co.edu.icesi.tintegracion.services.EmployeepayhistoryService;
import co.edu.icesi.tintegracion.services.PersonService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TintegracionApplication.class)
public class TintegracionApplicationTests {

	@Mock
	private BussinesEntityRepositoryInt bussinesEntityRepositoryInt;

	@Mock
	private DepartmentRepositoryInt departmentRepository;

	@Mock
	private EmployeeDepartmentHistoryRepositoryInt employeeDepartmentHistoryRepositoryInt;

	@Mock
	private EmployeePayHistoryRepositoryInt employeePayHistoryInt;

	@InjectMocks
	private EmployeepayhistoryService employeepayhistoryService;

	@Mock
	private EmployeeRepositoryInt employeeRepositoryInt;

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private PersonRepositoryInt personRepositoryInt;
	@InjectMocks
	private PersonService personService;

	@Mock
	private ShiftRepositoryInt shiftRepositoryInt;

	@Autowired
	public TintegracionApplicationTests(PersonService personService, EmployeeService employeeService,
			EmployeepayhistoryService employeepayhistoryService) {
		this.personService = personService;
		this.employeeService = employeeService;
		this.employeepayhistoryService = employeepayhistoryService;
	}

	@BeforeAll
	public void setup() {
		Person person = new Person();
		when(personRepositoryInt.findById(0)).thenReturn(Optional.of(person));

	}

	// 1
	@Test
	@Order(1)
	public void saveEmployee() throws ParseException {

		Businessentity bussinesEntity = new Businessentity();

		bussinesEntity.setBusinessentityid(0);
		int bussinesEntityId = bussinesEntity.getBusinessentityid();

		Person person = personService.findById(0).get();

		bussinesEntity.setPerson(person);
		Employee employee = new Employee();

		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

		employee.setHiredate(df.parse("01/01/2000"));

		employee.setJobtitle("Supervisor");

		employee.setBusinessentityid(bussinesEntityId);

		employee.setGender("gender1");

		when(employeeRepositoryInt.findById(0)).thenReturn(Optional.of(employee));

		//employeeService.save(employee);

		Employee theEmployee = employeeService.findById(0).get();

		assertFalse(theEmployee.getGender().isEmpty());
		assertFalse(theEmployee.getGender().isBlank());

		assertNotNull(theEmployee.getHiredate());

		assertTrue(theEmployee.getJobtitle().length() >= 5);

		verify(employeeRepositoryInt).save(employee);

		verify(employeeRepositoryInt).findById(0);

	}

	@Test
	@Order(2)
	public void editEmployee() {

		// Employee theEmployee = employeeService.findById(0).get();

	}

	@Test
	@Order(3)
	public void saveEmployeePayStory() {
		Employee theEmployee = employeeService.findById(0).get();

		Employeepayhistory employeepayhistory = new Employeepayhistory();

		employeepayhistory.setEmployee(theEmployee);

		employeepayhistory.setRate(new BigDecimal(1));

		employeepayhistory.setPayfrequency(15);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		employeepayhistory.setModifieddate(timestamp);

		Businessentity bussinesEntity = new Businessentity();

		bussinesEntity.setBusinessentityid(0);

		EmployeepayhistoryPK employeepayhistoryPk = new EmployeepayhistoryPK();

		employeepayhistoryPk.setBusinessentityid(bussinesEntity.getBusinessentityid());

		employeepayhistory.setId(employeepayhistoryPk);
		//employeepayhistoryService.save(employeepayhistory);

		when(employeePayHistoryInt.findById(employeepayhistoryPk)).thenReturn(Optional.of(employeepayhistory));

		Employeepayhistory theEmployeepayhistory = employeepayhistoryService.findById(employeepayhistoryPk);

		assertNotNull(theEmployeepayhistory.getEmployee());

		assertTrue(theEmployeepayhistory.getRate().doubleValue() > 0);

		assertTrue(theEmployeepayhistory.getPayfrequency() == 15 || theEmployeepayhistory.getPayfrequency() == 30);

		assertTrue(theEmployeepayhistory.getModifieddate().equals(timestamp));
		verify(employeePayHistoryInt).findById(employeepayhistoryPk);
	}

}