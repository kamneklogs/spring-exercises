package co.edu.icesi.tintegracion.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.repositories.EmployeeRepositoryInt;

@Service
public class EmployeeService {

    private EmployeeRepositoryInt employeeRepositoryInt;

    public EmployeeService(EmployeeRepositoryInt employeeRepositoryInt) {
        this.employeeRepositoryInt = employeeRepositoryInt;
    }
    /*
     * public Employee saveEmployee(Employee employee) {
     * 
     * employeeRepositoryInt.save(employee);
     * 
     * return employee;
     * 
     * }
     */

    public Employee save(Employee employee) {
        return employeeRepositoryInt.save(employee);
    }

    public Optional<Employee> findById(int i) {
        return employeeRepositoryInt.findById(i);
    }

    public Employee editEmployee() {
        return null;
    }

}
