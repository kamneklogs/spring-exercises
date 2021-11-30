package co.edu.icesi.tintegracion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
