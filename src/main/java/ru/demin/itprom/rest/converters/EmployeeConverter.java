package ru.demin.itprom.rest.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demin.itprom.jpa.entities.Employee;
import ru.demin.itprom.rest.represents.EmployeeRepresent;
import ru.demin.itprom.services.interfaces.DepartmentService;
import ru.demin.itprom.services.interfaces.ProfessionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeConverter {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProfessionService professionService;

    public EmployeeRepresent convertToRepresent(Employee employee) {
        EmployeeRepresent employeeRepresent = new EmployeeRepresent();
        employeeRepresent.setId(employee.getId().toString());
        employeeRepresent.setFio(employee.getFio());
        employeeRepresent.setDepartment(employee.getDepartment().getName());
        employeeRepresent.setProfession(employee.getProfession().getName());
        employeeRepresent.setNote(employee.getNote());
        return  employeeRepresent;
    }

    public List<EmployeeRepresent> convertToRepresent(List<Employee> employeeList) {
        return employeeList.stream().map(employee -> convertToRepresent(employee)).collect(Collectors.toList());
    }

    public Employee convertToEmployee(EmployeeRepresent employeeRepresent) {
        Employee employee = new Employee();
        if (employeeRepresent.getId() != null) employee.setId(Long.parseLong(employeeRepresent.getId()));
        employee.setFio(employeeRepresent.getFio());
        employee.setDepartment(departmentService.getDepartmentById(Long.parseLong(employeeRepresent.getDepartment())));
        employee.setProfession(professionService.getProfessionById(Long.parseLong(employeeRepresent.getProfession())));
        employee.setNote(employeeRepresent.getNote());
        return employee;
    }
}
