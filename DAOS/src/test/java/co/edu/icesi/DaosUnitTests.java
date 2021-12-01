package co.edu.icesi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.tintegracion.TintegracionApplication;
import co.edu.icesi.tintegracion.dao.PersonDao;
import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TintegracionApplication.class)
public class DaosUnitTests {

    @Autowired
    private PersonService personService;

    @Autowired
    PersonDao personDao;

    @Test
    public void findByIdTest() {

        Person person = new Person();

        person.setFirstname("f1");
        person.setLastname("l1");
        person.setTitle("xxx");

        personService.save(person);

        assertEquals(person.getFirstname(), personService.findById(person.getBusinessentityid()).getFirstname());

    }

}
