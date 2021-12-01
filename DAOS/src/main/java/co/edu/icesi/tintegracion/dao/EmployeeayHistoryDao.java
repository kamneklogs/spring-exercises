package co.edu.icesi.tintegracion.dao;

import java.util.Date;
import java.util.List;

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("singleton")
public class EmployeeayHistoryDao implements IEmployeeayHistoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Employeepayhistory entity) {
        entityManager.persist(entity);

    }

    @Transactional
    @Override
    public void update(Employeepayhistory entity) {
        entityManager.merge(entity);

    }

    @Transactional
    @Override
    public void delete(Employeepayhistory entity) {
        entityManager.remove(entity);

    }

    @Override
    public Employeepayhistory findById(Integer codigo) {
        return entityManager.find(Employeepayhistory.class, codigo);

    }

    @Override
    public List<Employeepayhistory> findByModifiedDate(Date date) {
        String jpql = "Select a from Employeepayhistory a where a.modifieddate = :" + date;

        return entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public List<Employeepayhistory> findAll() {
        String jpql = "Select a from Employeepayhistory a";
        return entityManager.createQuery(jpql).getResultList();

    }

    @Override
    public List<Employeepayhistory> findAllEmployeesPayHistory() {
        String jpql = " (Select a from Employeepayhistory a, Employeedepartmenthistory b where a.employee.businessentityid = b.businessentityid AND (Select count(h) from Employeedepartmenthistory h"
                + " Where h.employee.businessentityid = a.businessentityid) > 1";

        Query query = entityManager.createQuery(jpql);

        return query.getResultList();
    }

}
