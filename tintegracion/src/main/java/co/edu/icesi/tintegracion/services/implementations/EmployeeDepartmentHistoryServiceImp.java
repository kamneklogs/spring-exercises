package co.edu.icesi.tintegracion.services.implementations;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;
import co.edu.icesi.tintegracion.model.hr.Shift;
import co.edu.icesi.tintegracion.repositories.EmployeeDepartmentHistoryRepositoryInt;
import co.edu.icesi.tintegracion.repositories.EmployeeRepositoryInt;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeDepartmentHistoryService;

@Service
public class EmployeeDepartmentHistoryServiceImp implements EmployeeDepartmentHistoryService{

    private EmployeeDepartmentHistoryRepositoryInt employeeDepartmentHistoryRepositoryInt;
    private EmployeeRepositoryInt employeeRepositoryInt;

    public EmployeeDepartmentHistoryServiceImp(
            EmployeeDepartmentHistoryRepositoryInt employeeDepartmentHistoryRepositoryInt,
            EmployeeRepositoryInt employeeRepositoryInt) {
        this.employeeDepartmentHistoryRepositoryInt = employeeDepartmentHistoryRepositoryInt;
        this.employeeRepositoryInt = employeeRepositoryInt;
    }

    public Employeedepartmenthistory save(Employeedepartmenthistory employeedepartmenthistory, Integer employeeId) {

        Optional<Employee> employee = employeeRepositoryInt.findById(employeeId);

        if (!employee.isPresent()) {
            throw new RuntimeException("Employee not found");
        } else {
            employeedepartmenthistory.setEmployee(employee.get());

            Timestamp actualdate = new Timestamp(System.currentTimeMillis());

            if (!actualdate.before(employeedepartmenthistory.getEnddate())) {
                throw new RuntimeException("End date is not valid");
            }

        }

        Shift shift = employeedepartmenthistory.getShift();

        if (shift == null) {
            throw new RuntimeException("Shift cannot be null");
        } else if (shift.getName() == null) {
            throw new RuntimeException("Shift name cannot be null");
        } else if (employeedepartmenthistory.getDepartment() == null) {
            throw new RuntimeException("Department cannot be null");
        }

        employeedepartmenthistory.setModifieddate(new Timestamp(System.currentTimeMillis()));
        return employeeDepartmentHistoryRepositoryInt.save(employeedepartmenthistory);
    }

    public Employeedepartmenthistory edit(Employeedepartmenthistory employeedepartmenthistory, Integer employeeId) {

        Optional<Employee> employee = employeeRepositoryInt.findById(employeeId);

        if (!employee.isPresent()) {
            throw new RuntimeException("Employee not found");
        } else {
            employeedepartmenthistory.setEmployee(employee.get());
            Timestamp actualdate = new Timestamp(System.currentTimeMillis());

            if (!actualdate.before(employeedepartmenthistory.getEnddate())) {
                throw new RuntimeException("End date is not valid");
            }

        }

        Shift shift = employeedepartmenthistory.getShift();

        if (shift == null) {
            throw new RuntimeException("Shift cannot be null");
        } else if (shift.getName() == null) {
            throw new RuntimeException("Shift name cannot be null");
        } else if (employeedepartmenthistory.getDepartment() == null) {
            throw new RuntimeException("Department cannot be null");
        }

        employeedepartmenthistory.setModifieddate(new Timestamp(System.currentTimeMillis()));
        return employeeDepartmentHistoryRepositoryInt.save(employeedepartmenthistory);
    }
}
