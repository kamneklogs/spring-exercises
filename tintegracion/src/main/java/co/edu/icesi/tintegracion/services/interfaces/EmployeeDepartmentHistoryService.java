package co.edu.icesi.tintegracion.services.interfaces;

import co.edu.icesi.tintegracion.model.hr.Department;
import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;

public interface EmployeeDepartmentHistoryService {

    public Employeedepartmenthistory save(Employeedepartmenthistory employeedepartmenthistory, Integer departmentId);
    public Iterable<Employeedepartmenthistory> findAll();
    public Employeedepartmenthistory edit(Employeedepartmenthistory employeedepartmenthistory, Integer employeeId);
}
