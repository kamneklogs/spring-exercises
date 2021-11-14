package co.edu.icesi.tintegracion.services.implementations;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.hr.Department;
import co.edu.icesi.tintegracion.repositories.DepartmentRepositoryInt;
import co.edu.icesi.tintegracion.services.interfaces.DepartmentService;

@Service
public class DepartmentServiceImp implements DepartmentService {

    private DepartmentRepositoryInt departmentRepositoryInt;

    public DepartmentServiceImp(DepartmentRepositoryInt departmentRepositoryInt) {
        this.departmentRepositoryInt = departmentRepositoryInt;
    }

    public Department save(Department department) {

        department.setModifieddate(new Timestamp(System.currentTimeMillis()));
        return departmentRepositoryInt.save(department);
    }

    public Department edit(Department department) {

        if (department.getGroupname() == null || department.getGroupname().length() < 5) {
            throw new RuntimeException("Department group name is invalid");
        } else if (department.getName() == null || department.getName().length() < 5) {
            throw new RuntimeException("Department name is invalid");
        }

        department.setModifieddate(new Timestamp(System.currentTimeMillis()));
        return departmentRepositoryInt.save(department);
    }

    public Iterable<Department> findAll() {
        return departmentRepositoryInt.findAll();
    }

}
