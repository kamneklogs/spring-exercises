package co.edu.icesi.tintegracion.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.model.hr.EmployeepayhistoryPK;
import co.edu.icesi.tintegracion.repositories.EmployeePayHistoryRepositoryInt;
import co.edu.icesi.tintegracion.repositories.EmployeeRepositoryInt;

@Service
public class EmployeepayhistoryService {

    private EmployeePayHistoryRepositoryInt employeePayHistoryRepositoryInt;
    private EmployeeRepositoryInt employeeRepositoryInt;

    public EmployeepayhistoryService(EmployeePayHistoryRepositoryInt employeePayHistoryRepositoryInt,
            EmployeeRepositoryInt employeeRepositoryInt) {
        this.employeePayHistoryRepositoryInt = employeePayHistoryRepositoryInt;
        this.employeeRepositoryInt = employeeRepositoryInt;
    }

    public Employeepayhistory save(Integer employeeId, Employeepayhistory employeepayhistory,
            EmployeepayhistoryPK employeepayhistoryPk) {

        Optional<Employee> employee = employeeRepositoryInt.findById(employeeId);

        if (!employee.isPresent()) {
            throw new RuntimeException("Employee not found");
        } else if (employeepayhistory == null) {
            throw new RuntimeException("Employeepayhistory cannot be null");
        } else if (employeepayhistory.getRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Rate is less than zero");
        } else if (!(employeepayhistory.getPayfrequency() == 15) || !(employeepayhistory.getPayfrequency() == 30)) {
            throw new RuntimeException("Payfrequency is invalid");
        }

        employeepayhistory.setId(employeepayhistoryPk);

        employeepayhistory.setModifieddate(new Timestamp(System.currentTimeMillis()));

        return employeePayHistoryRepositoryInt.save(employeepayhistory);
    }

    public Employeepayhistory edit(Integer employeeId, Employeepayhistory employeepayhistory,
            EmployeepayhistoryPK employeepayhistoryPk) {

        Optional<Employee> employee = employeeRepositoryInt.findById(employeeId);

        Optional<Employeepayhistory> employeepayhistoryToEdit = employeePayHistoryRepositoryInt
                .findById(employeepayhistoryPk);

        if (!employeepayhistoryToEdit.isPresent()) {
            throw new RuntimeException("Employeepayhistory not found");
        } else {

            if (!employee.isPresent()) {
                throw new RuntimeException("Employee not found");
            } else if (employeepayhistory == null) {
                throw new RuntimeException("Employeepayhistory cannot be null");
            } else if (employeepayhistory.getRate().compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Rate is less than zero");
            } else if (!(employeepayhistory.getPayfrequency() == 15) || !(employeepayhistory.getPayfrequency() == 30)) {
                throw new RuntimeException("Payfrequency is invalid");
            }
        }

        Employeepayhistory newEmployeepayHistory = employeepayhistoryToEdit.get();

        newEmployeepayHistory.setPayfrequency(employeepayhistory.getPayfrequency());
        newEmployeepayHistory.setRate(employeepayhistory.getRate());

        newEmployeepayHistory.setId(employeepayhistoryPk);

        newEmployeepayHistory.setModifieddate(new Timestamp(System.currentTimeMillis()));

        return employeePayHistoryRepositoryInt.save(newEmployeepayHistory);
    }

    public Employeepayhistory findById(EmployeepayhistoryPK employeepayhistoryPk) {
        return employeePayHistoryRepositoryInt.findById(employeepayhistoryPk).get();
    }

}
