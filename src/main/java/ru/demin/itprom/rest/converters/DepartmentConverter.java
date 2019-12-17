package ru.demin.itprom.rest.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demin.itprom.jpa.entities.Department;
import ru.demin.itprom.rest.represents.DepartmentRepresent;
import ru.demin.itprom.services.interfaces.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentConverter {

    @Autowired
    private DepartmentService departmentService;

    public DepartmentRepresent convertToRepresent (Department department) {
        DepartmentRepresent departmentRepresent = new DepartmentRepresent();
        departmentRepresent.setId(department.getId().toString());
        departmentRepresent.setName(department.getName());
        departmentRepresent.setNote(department.getNote());
        if (department.getParent() != null) departmentRepresent.setParent(department.getParent().getName());
        return departmentRepresent;
    }

    public List<DepartmentRepresent> convertToRepresent (List<Department> departmentList) {
        return departmentList.stream().map(department -> convertToRepresent(department)).collect(Collectors.toList());
    }

    public Department convertToDepartment (DepartmentRepresent departmentRepresent) {
        Department department = new Department();
        if (departmentRepresent.getId() != null) department.setId(Long.parseLong(departmentRepresent.getId()));
        department.setName(departmentRepresent.getName());
        department.setNote(departmentRepresent.getNote());
        if (!departmentRepresent.getParent().isEmpty()) department.setParent(departmentService.getDepartmentById(Long.parseLong(departmentRepresent.getParent())));
        return department;
    }
}
