package ru.demin.itprom.services.interfaces;

import ru.demin.itprom.jpa.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    List<Employee> getAllEmployeesByProfessionId(Long professionId);
    List<Employee> getAllEmployeesByDepartmentId(Long professionId);
    void deleteEmployee(Long id);
}
