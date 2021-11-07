package co.edu.icesi.tintegracion.services.interfaces;

import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;

public interface EmployeeDepartmentHistoryServiceInt {

    public Employeedepartmenthistory save(Employeedepartmenthistory employeedepartmenthistory, Integer employeeId);

    public Employeedepartmenthistory edit(Employeedepartmenthistory employeedepartmenthistory, Integer employeeId);
}
