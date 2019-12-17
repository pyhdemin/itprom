package ru.demin.itprom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demin.itprom.jpa.entities.Employee;
import ru.demin.itprom.jpa.repositories.EmployeeRepository;
import ru.demin.itprom.services.interfaces.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployeesByProfessionId(Long professionId) {
        return employeeRepository.findByProfessionId(professionId);
    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentId(Long professionId) {
        return employeeRepository.findByDepartmentId(professionId);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }



}
