package co.edu.icesi.tintegracion.services.interfaces;

import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;

public interface EmployeeDepartmentHistoryService {

    public Employeedepartmenthistory save(Employeedepartmenthistory employeedepartmenthistory);
    public Iterable<Employeedepartmenthistory> findAll();
    public Employeedepartmenthistory findById(Integer id);
    public Employeedepartmenthistory edit(Employeedepartmenthistory employeedepartmenthistory, Integer employeeId);
}
