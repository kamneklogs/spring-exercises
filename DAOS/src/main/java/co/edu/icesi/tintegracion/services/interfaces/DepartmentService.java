package co.edu.icesi.tintegracion.services.interfaces;

import co.edu.icesi.tintegracion.model.hr.Department;

public interface DepartmentService {
    public Department save(Department department);

    public Department edit(Department department);

    public Iterable<Department> findAll();

    public Department findById(Integer id);
}
