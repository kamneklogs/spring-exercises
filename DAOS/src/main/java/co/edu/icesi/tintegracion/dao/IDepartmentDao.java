package co.edu.icesi.tintegracion.dao;

import java.util.List;

import co.edu.icesi.tintegracion.model.hr.Department;

public interface IDepartmentDao {
    public void save(Department entity);

    public void update(Department entity);

    public void delete(Department entity);

    public Department findById(Integer codigo);

    public List<Department> findByGroupName(String groupName);

    public List<Department> findAll();
}
