package co.edu.icesi.tintegracion.services.interfaces;

import java.util.Optional;

import co.edu.icesi.tintegracion.model.hr.Department;

public interface DepartmentService {
    public Department save(Department department);

    public Department edit(Department department);

    public Iterable<Department> findAll();

    public Optional<Department> findById(Integer id);
}
