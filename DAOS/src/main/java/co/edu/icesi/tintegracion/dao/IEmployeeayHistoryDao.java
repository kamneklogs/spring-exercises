package co.edu.icesi.tintegracion.dao;

import java.util.Date;
import java.util.List;

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;

public interface IEmployeeayHistoryDao {
    public void save(Employeepayhistory entity);

    public void update(Employeepayhistory entity);

    public void delete(Employeepayhistory entity);

    public Employeepayhistory findById(Integer codigo);

    public List<Employeepayhistory> findByModifiedDate(Date date);

    public List<Employeepayhistory> findAll();

    public List<Employeepayhistory> findAllEmployeesPayHistory();

}
