package co.edu.icesi.tintegracion.services.interfaces;

import java.util.Optional;

import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.model.users.UserSystem;
import co.edu.icesi.tintegracion.model.users.UserType;

public interface UserService {

    public UserSystem save(UserSystem user);

    public Optional<UserSystem> findByUsername(String username);

    public Iterable<UserSystem> findAll();

    public Iterable<UserSystem> findAllAdministrators();

    public Iterable<UserSystem> findAllOperators();

    public void delete(UserSystem user);

    public UserType[] getTypes();
}
