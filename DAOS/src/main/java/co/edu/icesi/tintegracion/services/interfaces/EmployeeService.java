package co.edu.icesi.tintegracion.services.interfaces;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import co.edu.icesi.tintegracion.model.hr.Employee;

public interface EmployeeService {
    public Employee save(Employee employee);

    public Employee edit(Employee employee, Integer personId, Integer businessId);

    public Employee findById(int i);

    public Iterable<Employee> findByTittle(String tittle);

    public Iterable<Employee> findAll();

    public Iterable<Employee> findByHireDate(Date date);

    public List<Employee> findAllEmployeesWithCountDeparments(Timestamp startdate, Timestamp enddate);

}
