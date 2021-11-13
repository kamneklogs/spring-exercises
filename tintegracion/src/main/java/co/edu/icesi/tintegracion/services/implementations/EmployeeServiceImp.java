package co.edu.icesi.tintegracion.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.person.Businessentity;
import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.repositories.BussinesEntityRepositoryInt;
import co.edu.icesi.tintegracion.repositories.EmployeeRepositoryInt;
import co.edu.icesi.tintegracion.repositories.PersonRepositoryInt;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeRepositoryInt employeeRepositoryInt;

    private PersonRepositoryInt personRepositoryInt;

    private BussinesEntityRepositoryInt businessEntityRepositoryInt;

    public EmployeeServiceImp(EmployeeRepositoryInt employeeRepositoryInt, PersonRepositoryInt personRepositoryInt,
            BussinesEntityRepositoryInt businessEntityRepositoryInt) {
        this.employeeRepositoryInt = employeeRepositoryInt;
        this.personRepositoryInt = personRepositoryInt;
        this.businessEntityRepositoryInt = businessEntityRepositoryInt;
    }

    public Employee save(Employee employee) {

        log.info(employee.getBusinessentityid()+"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return employeeRepositoryInt.save(employee);
    }

    public Employee edit(Employee employee, Integer personId, Integer businessId) {

        Optional<Person> person = personRepositoryInt.findById(personId);
        Optional<Businessentity> bussinessEntity = businessEntityRepositoryInt.findById(businessId);
        Optional<Employee> employeeToEdit = employeeRepositoryInt.findById(employee.getBusinessentityid());

        if (employee == null) {
            throw new RuntimeException("Employee cannot be null");
        } else if (!employeeToEdit.isPresent()) {
            if (person.isEmpty()) {
                throw new RuntimeException("Person not exists");
            } else if (bussinessEntity.isEmpty()) {
                throw new RuntimeException("Business entity not exists");
            } else if (employee.getGender() == null) {
                throw new RuntimeException("employee gender cannot be null");
            } else if (employee.getHiredate() == null) {
                throw new RuntimeException("Employee hiredate cannot be null");
            } else if (employee.getJobtitle() == null || employee.getJobtitle().length() < 5) {
                throw new RuntimeException("Employee jobtitle invalid");
            }
        }

        Employee newEmployee = employeeToEdit.get();

        newEmployee.setGender(employee.getGender());
        newEmployee.setHiredate(employee.getHiredate());
        newEmployee.setJobtitle(employee.getJobtitle());

        bussinessEntity.get().setPerson(person.get());
        person.get().setBusinessentity(bussinessEntity.get());
        newEmployee.setBusinessentityid(businessId);

        return employeeRepositoryInt.save(newEmployee);
    }

    public Optional<Employee> findById(int i) {
        return employeeRepositoryInt.findById(i);
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepositoryInt.findAll();
    }

}
