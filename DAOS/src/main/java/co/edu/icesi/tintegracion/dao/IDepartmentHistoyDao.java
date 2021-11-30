package co.edu.icesi.tintegracion.dao;

import java.util.List;

import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;

public interface IDepartmentHistoyDao {
    public void save(Employeedepartmenthistory entity);

    public void update(Employeedepartmenthistory entity);

    public void delete(Employeedepartmenthistory entity);

    public Employeedepartmenthistory findById(Integer codigo);

    public List<Employeedepartmenthistory> findAll();
}
