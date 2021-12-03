package co.edu.icesi.tintegracion.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.tintegracion.model.hr.Employee;

@Repository
@Scope("singleton")
public class EmployeeDao implements IEmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Employee entity) {
        entityManager.persist(entity);

    }

    @Transactional
    @Override
    public void update(Employee entity) {
        entityManager.merge(entity);

    }

    @Transactional
    @Override
    public void delete(Employee entity) {
        entityManager.remove(entity);

    }

    @Override
    public Employee findById(Integer codigo) {
        return entityManager.find(Employee.class, codigo);

    }

    @Override
    public List<Employee> findAll() {
        String jpql = "Select a from Employee a";
        return entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public List<Employee> findByTittle(String tittle) {
        String jpql = "Select a from Employee a where a.jobtitle = '" + tittle + "'";
        return entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public List<Employee> findByHireDate(Date date) {
        String jpql = "Select a from Employee a where a.hiredate = :date";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Employee> findAllEmployeesWithCountDeparments(Timestamp startdate, Timestamp enddate) {

        String jpql = "Select a from Employee a where a.hiredate >= :startdate AND a.hiredate <= :enddate AND(Select count(h) from Employeedepartmenthistory h"
                + " Where h.employee.businessentityid = a.businessentityid And h.startdate >= :startdate AND h.enddate <= :enddate) > 0";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("startdate", startdate);
        query.setParameter("enddate", enddate);

        return query.getResultList();
    }

   

}
