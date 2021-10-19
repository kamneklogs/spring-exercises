package co.edu.icesi.tintegracion.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.model.hr.EmployeepayhistoryPK;

public interface EmployeePayHistoryRepositoryInt extends CrudRepository<Employeepayhistory, EmployeepayhistoryPK> {

}
