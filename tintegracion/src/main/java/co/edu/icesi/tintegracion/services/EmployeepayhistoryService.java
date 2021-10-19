package co.edu.icesi.tintegracion.services;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.model.hr.EmployeepayhistoryPK;
import co.edu.icesi.tintegracion.repositories.EmployeePayHistoryRepositoryInt;

@Service
public class EmployeepayhistoryService {

    private EmployeePayHistoryRepositoryInt employeePayHistoryRepositoryInt;

    public EmployeepayhistoryService(EmployeePayHistoryRepositoryInt employeePayHistoryRepositoryInt) {
        this.employeePayHistoryRepositoryInt = employeePayHistoryRepositoryInt;
    }

    public Employeepayhistory save(Employeepayhistory employeepayhistory) {
        return employeePayHistoryRepositoryInt.save(employeepayhistory);
    }

    public Employeepayhistory findById(EmployeepayhistoryPK employeepayhistoryPk) {
        return employeePayHistoryRepositoryInt.findById(employeepayhistoryPk).get();
    }

}
