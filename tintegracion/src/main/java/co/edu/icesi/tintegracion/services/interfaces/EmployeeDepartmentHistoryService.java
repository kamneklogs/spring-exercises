package co.edu.icesi.tintegracion.services.interfaces;

import java.util.Optional;

import co.edu.icesi.tintegracion.model.hr.Department;
import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;

public interface EmployeeDepartmentHistoryService {

    public Employeedepartmenthistory save(Employeedepartmenthistory employeedepartmenthistory);
    public Iterable<Employeedepartmenthistory> findAll();
    public Optional<Employeedepartmenthistory> findById(Integer id);
    public Employeedepartmenthistory edit(Employeedepartmenthistory employeedepartmenthistory, Integer employeeId);
}
