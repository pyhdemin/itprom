package ru.demin.itprom.services.interfaces;

import ru.demin.itprom.exceptions.DepartmentUseAsParent;
import ru.demin.itprom.exceptions.DepartmentUseInEmployeeException;
import ru.demin.itprom.jpa.entities.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    Department saveDepartment(Department department);
    Department updateDepartment(Department department);
    void deleteDepartment(Long id) throws DepartmentUseInEmployeeException, DepartmentUseAsParent;
    List<Department> getPossibleParentDepartments(Long id);
}
