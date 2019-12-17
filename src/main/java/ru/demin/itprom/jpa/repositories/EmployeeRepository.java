package ru.demin.itprom.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demin.itprom.jpa.entities.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByProfessionId(Long professionId);
    List<Employee> findByDepartmentId(Long departmentId);
}
