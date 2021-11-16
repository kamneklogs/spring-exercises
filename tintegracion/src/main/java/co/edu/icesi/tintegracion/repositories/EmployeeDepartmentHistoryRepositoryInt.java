package co.edu.icesi.tintegracion.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;
import co.edu.icesi.tintegracion.model.hr.EmployeedepartmenthistoryPK;

public interface EmployeeDepartmentHistoryRepositoryInt extends CrudRepository<Employeedepartmenthistory, Integer> {

}
