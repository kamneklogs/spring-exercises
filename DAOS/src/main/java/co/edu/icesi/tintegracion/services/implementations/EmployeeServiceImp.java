package co.edu.icesi.tintegracion.services.implementations;

import java.util.Date;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.dao.EmployeeDao;
import co.edu.icesi.tintegracion.dao.PersonDao;
import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.repositories.BussinesEntityRepositoryInt;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeDao employeeRepositoryInt;

    public EmployeeServiceImp(EmployeeDao employeeRepositoryInt, PersonDao personRepositoryInt,
            BussinesEntityRepositoryInt businessEntityRepositoryInt) {
        this.employeeRepositoryInt = employeeRepositoryInt;
    }

    public Employee save(Employee employee) {

        employee.setHiredate(new Date());
        employeeRepositoryInt.save(employee);

        return employeeRepositoryInt.findById(employee.getBusinessentityid());
    }

    public Employee edit(Employee employee, Integer personId, Integer businessId) {

        Employee employeeToEdit = employeeRepositoryInt.findById(employee.getBusinessentityid());

        Employee newEmployee = employeeToEdit;

        newEmployee.setGender(employee.getGender());
        newEmployee.setHiredate(employee.getHiredate());
        newEmployee.setJobtitle(employee.getJobtitle());

        newEmployee.setBusinessentityid(businessId);

        employee.setBusinessentityid(employeeToEdit.getBusinessentityid());
        employee.setHiredate(new Date());
        employeeRepositoryInt.update(employee);
        return employeeRepositoryInt.findById(newEmployee.getBusinessentityid());
    }

    public Employee findById(int i) {
        return employeeRepositoryInt.findById(i);
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepositoryInt.findAll();
    }

}
