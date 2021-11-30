package co.edu.icesi.tintegracion.dao;

import java.util.List;

import co.edu.icesi.tintegracion.model.person.Person;

public interface IPersonDao {
    public void save(Person entity);

    public void update(Person entity);

    public void delete(Person entity);

    public Person findById(Integer codigo);

    public List<Person> findAll();
}
