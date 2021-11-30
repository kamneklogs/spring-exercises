package co.edu.icesi.tintegracion.services.interfaces;

import java.util.Optional;

import co.edu.icesi.tintegracion.model.hr.Employee;

public interface EmployeeService {
    public Employee save(Employee employee);

    public Employee edit(Employee employee, Integer personId, Integer businessId);

    public Employee findById(int i);

    public Employee findByTittle(String tittle);

    public Iterable<Employee> findAll();

}
