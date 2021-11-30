package co.edu.icesi.tintegracion.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.tintegracion.model.hr.Employee;

public interface EmployeeRepositoryInt extends CrudRepository<Employee, Integer>{
    
}
