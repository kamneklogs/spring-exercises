package co.edu.icesi.tintegracion.services;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import co.edu.icesi.tintegracion.model.hr.Department;
import co.edu.icesi.tintegracion.repositories.DepartmentRepositoryInt;

@Service
public class DepartmentService {

    private DepartmentRepositoryInt departmentRepositoryInt;

    public DepartmentService(DepartmentRepositoryInt departmentRepositoryInt) {
        this.departmentRepositoryInt = departmentRepositoryInt;
    }

    public Department save(Department department) {

        if (department.getGroupname() == null || department.getGroupname().length() < 5) {
            throw new RuntimeException("Department group name is invalid");
        } else if (department.getName() == null || department.getName().length() < 5) {
            throw new RuntimeException("Department name is invalid");
        }

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

}
