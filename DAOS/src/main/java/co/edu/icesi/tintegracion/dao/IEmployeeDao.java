package co.edu.icesi.tintegracion.dao;

import java.util.List;

import co.edu.icesi.tintegracion.model.hr.Employee;

public interface IEmployeeDao {
    public void save(Employee entity);

    public void update(Employee entity);

    public void delete(Employee entity);

    public Employee findById(Integer codigo);

    public List<Employee> findAll();
}
