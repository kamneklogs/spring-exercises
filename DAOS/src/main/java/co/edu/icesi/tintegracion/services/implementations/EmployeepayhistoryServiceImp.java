package co.edu.icesi.tintegracion.services.implementations;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.dao.EmployeeDao;
import co.edu.icesi.tintegracion.dao.EmployeeayHistoryDao;
import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.services.interfaces.EmployeePayHistoryService;

@Service
public class EmployeepayhistoryServiceImp implements EmployeePayHistoryService {

    private EmployeeayHistoryDao employeePayHistoryRepositoryInt;
    private EmployeeDao employeeRepositoryInt;

    public EmployeepayhistoryServiceImp(EmployeeayHistoryDao employeePayHistoryRepositoryInt,
            EmployeeDao employeeRepositoryInt) {
        this.employeePayHistoryRepositoryInt = employeePayHistoryRepositoryInt;
        this.employeeRepositoryInt = employeeRepositoryInt;
    }

    public Employeepayhistory save(Employeepayhistory employeepayhistory, Integer employeeId) {

        employeepayhistory.setModifieddate(new Timestamp(System.currentTimeMillis()));

        employeePayHistoryRepositoryInt.save(employeepayhistory);

        return employeePayHistoryRepositoryInt.findById(employeepayhistory.getBusinessentityid());
    }

    public Employeepayhistory edit(Integer employeeId, Employeepayhistory employeepayhistory,
            Integer employeepayhistoryId) {

        Employee employee = employeeRepositoryInt.findById(employeeId);

        Employeepayhistory employeepayhistoryToEdit = employeePayHistoryRepositoryInt
                .findById(employeepayhistoryId);

        if (!(employeepayhistoryToEdit != null)) {
            throw new RuntimeException("Employeepayhistory not found");
        } else {

            if (!(employee != null)) {
                throw new RuntimeException("Employee not found");
            } else if (employeepayhistory == null) {
                throw new RuntimeException("Employeepayhistory cannot be null");
            } else if (employeepayhistory.getRate().compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Rate is less than zero");
            } else if (!(employeepayhistory.getPayfrequency() == 15) || !(employeepayhistory.getPayfrequency() == 30)) {
                throw new RuntimeException("Payfrequency is invalid");
            }
        }

        Employeepayhistory newEmployeepayHistory = employeepayhistoryToEdit;

        newEmployeepayHistory.setPayfrequency(employeepayhistory.getPayfrequency());
        newEmployeepayHistory.setRate(employeepayhistory.getRate());

        newEmployeepayHistory.setModifieddate(new Timestamp(System.currentTimeMillis()));

        employeePayHistoryRepositoryInt.update(newEmployeepayHistory);

        return employeePayHistoryRepositoryInt.findById(newEmployeepayHistory.getBusinessentityid());
    }

    public Employeepayhistory findById(Integer employeepayhistoryPk) {
        return employeePayHistoryRepositoryInt.findById(employeepayhistoryPk);
    }

    @Override
    public Iterable<Employeepayhistory> findAll() {

        return employeePayHistoryRepositoryInt.findAll();
    }

}
