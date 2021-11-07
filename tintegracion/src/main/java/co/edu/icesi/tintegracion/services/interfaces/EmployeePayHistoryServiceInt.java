package co.edu.icesi.tintegracion.services.interfaces;

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.model.hr.EmployeepayhistoryPK;

public interface EmployeePayHistoryServiceInt {

    public Employeepayhistory save(Integer employeeId, Employeepayhistory employeepayhistory,
            EmployeepayhistoryPK employeepayhistoryPk);

    public Employeepayhistory edit(Integer employeeId, Employeepayhistory employeepayhistory,
            EmployeepayhistoryPK employeepayhistoryPk);

    public Employeepayhistory findById(EmployeepayhistoryPK employeepayhistoryPk);
}
