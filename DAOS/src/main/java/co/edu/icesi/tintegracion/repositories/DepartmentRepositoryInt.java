package co.edu.icesi.tintegracion.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.tintegracion.model.hr.Department;

public interface DepartmentRepositoryInt extends CrudRepository<Department, Integer> {

}
