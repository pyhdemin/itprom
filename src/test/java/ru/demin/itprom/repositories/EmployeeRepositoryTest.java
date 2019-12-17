package ru.demin.itprom.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.demin.itprom.jpa.entities.Employee;
import ru.demin.itprom.jpa.repositories.EmployeeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @DisplayName("Test to find Employees by professionId")
    @Test
    public void whenFindByProfessionId_thenReturnEmployees() {
        List<Employee> employees = employeeRepository.findByProfessionId(2L);
        assertEquals(employees.size(), 3);
    }

    @DisplayName("Test to find Employees by departmentId")
    @Test
    public void whenFindByDepartmentId_thenReturnEmployees() {
        List<Employee> employees = employeeRepository.findByDepartmentId(1L);
        assertEquals(employees.size(), 2);
    }

}