package co.edu.icesi.tintegracion.services.implementations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.repositories.EmployeePayHistoryRepositoryInt;
import co.edu.icesi.tintegracion.repositories.EmployeeRepositoryInt;
import co.edu.icesi.tintegracion.services.interfaces.EmployeePayHistoryService;

@Service
public class EmployeepayhistoryServiceImp implements EmployeePayHistoryService {

    private EmployeePayHistoryRepositoryInt employeePayHistoryRepositoryInt;
    private EmployeeRepositoryInt employeeRepositoryInt;

    public EmployeepayhistoryServiceImp(EmployeePayHistoryRepositoryInt employeePayHistoryRepositoryInt,
            EmployeeRepositoryInt employeeRepositoryInt) {
        this.employeePayHistoryRepositoryInt = employeePayHistoryRepositoryInt;
        this.employeeRepositoryInt = employeeRepositoryInt;
    }

    public Employeepayhistory save(Employeepayhistory employeepayhistory, Integer employeeId) {

       

        employeepayhistory.setModifieddate(new Timestamp(System.currentTimeMillis()));
       
        return employeePayHistoryRepositoryInt.save(employeepayhistory);
    }

    public Employeepayhistory edit(Integer employeeId, Employeepayhistory employeepayhistory,
            Integer employeepayhistoryId) {

        Optional<Employee> employee = employeeRepositoryInt.findById(employeeId);

        Optional<Employeepayhistory> employeepayhistoryToEdit = employeePayHistoryRepositoryInt
                .findById(employeepayhistoryId);

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

        newEmployeepayHistory.setModifieddate(new Timestamp(System.currentTimeMillis()));

        return employeePayHistoryRepositoryInt.save(newEmployeepayHistory);
    }

    public Employeepayhistory findById(Integer employeepayhistoryPk) {
        return employeePayHistoryRepositoryInt.findById(employeepayhistoryPk).get();
    }

    @Override
    public Iterable<Employeepayhistory> findAll() {

        return employeePayHistoryRepositoryInt.findAll();
    }

}
