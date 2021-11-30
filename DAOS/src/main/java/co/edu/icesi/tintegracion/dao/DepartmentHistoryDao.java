package co.edu.icesi.tintegracion.dao;

import java.util.List;

import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope("singleton")
public class DepartmentHistoryDao implements IDepartmentHistoyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Employeedepartmenthistory entity) {
        entityManager.persist(entity);

    }

    @Transactional
    @Override
    public void update(Employeedepartmenthistory entity) {
        entityManager.merge(entity);

    }

    @Transactional
    @Override
    public void delete(Employeedepartmenthistory entity) {
        entityManager.remove(entity);

    }

    @Override
    public Employeedepartmenthistory findById(Integer codigo) {
        return entityManager.find(Employeedepartmenthistory.class, codigo);
    }

    @Override
    public List<Employeedepartmenthistory> findAll() {
        String jpql = "Select a from Employeedepartmenthistory a";
        return entityManager.createQuery(jpql).getResultList();
    }

}
