package co.edu.icesi.tintegracion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.tintegracion.model.person.Person;

@Repository
@Scope("singleton")
public class PersonDao implements IPersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Person entity) {
        entityManager.persist(entity);

    }

    @Transactional
    @Override
    public void update(Person entity) {
        entityManager.merge(entity);

    }

    @Transactional
    @Override
    public void delete(Person entity) {
        entityManager.remove(entity);

    }

    @Override
    public Person findById(Integer codigo) {
        return entityManager.find(Person.class, codigo);
    }

    @Override
    public List<Person> findAll() {
        String jpql = "Select a from Person a";
        return entityManager.createQuery(jpql).getResultList();
    }
}
