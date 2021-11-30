package co.edu.icesi.tintegracion.dao;

import java.util.List;

import co.edu.icesi.tintegracion.model.hr.Department;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("singleton")
public class DepartmentDao implements IDepartmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Department entity) {
        entityManager.persist(entity);

    }

    @Transactional
    @Override
    public void update(Department entity) {
        entityManager.merge(entity);

    }

    @Transactional
    @Override
    public void delete(Department entity) {
        entityManager.remove(entity);

    }

    @Override
    public Department findById(Integer codigo) {
        return entityManager.find(Department.class, codigo);

    }

    @Override
    public List<Department> findByGroupName(String groupName) {
        String jpql = "Select a from Department a where a.groupname = '" + groupName + "'";

        return entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public List<Department> findAll() {
        String jpql = "Select a from Department a";
        return entityManager.createQuery(jpql).getResultList();
    }

}
