package co.edu.icesi.tintegracion.services.implementations;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.dao.DepartmentHistoryDao;
import co.edu.icesi.tintegracion.dao.EmployeeDao;
import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;
import co.edu.icesi.tintegracion.model.hr.Shift;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeDepartmentHistoryService;

@Service
public class EmployeeDepartmentHistoryServiceImp implements EmployeeDepartmentHistoryService {

    private DepartmentHistoryDao employeeDepartmentHistoryRepositoryInt;
    private EmployeeDao employeeRepositoryInt;

    @Autowired
    public EmployeeDepartmentHistoryServiceImp(
            DepartmentHistoryDao employeeDepartmentHistoryRepositoryInt,
            EmployeeDao employeeRepositoryInt) {
        this.employeeDepartmentHistoryRepositoryInt = employeeDepartmentHistoryRepositoryInt;
        this.employeeRepositoryInt = employeeRepositoryInt;
    }

    public Employeedepartmenthistory save(Employeedepartmenthistory employeedepartmenthistory) {

        employeedepartmenthistory.setModifieddate(new Timestamp(System.currentTimeMillis()));
        employeeDepartmentHistoryRepositoryInt.save(employeedepartmenthistory);
        return employeeDepartmentHistoryRepositoryInt.findById(employeedepartmenthistory.getBusinessentityid());
    }

    public Employeedepartmenthistory edit(Employeedepartmenthistory employeedepartmenthistory, Integer employeeId) {

        Employee employee = employeeRepositoryInt.findById(employeeId);

        if (!(employee != null)) {
            throw new RuntimeException("Employee not found");
        } else {
            employeedepartmenthistory.setEmployee(employee);
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
        employeeDepartmentHistoryRepositoryInt.update(employeedepartmenthistory);
        return employeeDepartmentHistoryRepositoryInt.findById(employeedepartmenthistory.getBusinessentityid());

    }

    @Override
    public Iterable<Employeedepartmenthistory> findAll() {

        return employeeDepartmentHistoryRepositoryInt.findAll();
    }

    @Override
    public Employeedepartmenthistory findById(Integer id) {

        return employeeDepartmentHistoryRepositoryInt.findById(id);
    }
}
