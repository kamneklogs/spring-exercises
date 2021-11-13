package co.edu.icesi.tintegracion.services.interfaces;

import java.util.List;

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.model.hr.EmployeepayhistoryPK;

public interface EmployeePayHistoryService {

    public Employeepayhistory save(Integer employeeId, Employeepayhistory employeepayhistory,
            EmployeepayhistoryPK employeepayhistoryPk);

    public Employeepayhistory edit(Integer employeeId, Employeepayhistory employeepayhistory,
            Integer employeepayhistoryPk);

    public Employeepayhistory findById(Integer employeepayhistoryPk);

    public Iterable<Employeepayhistory> findAll();
}
