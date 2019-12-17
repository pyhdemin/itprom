package ru.demin.itprom.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demin.itprom.jpa.entities.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> getAllByParentId(Long id);
    List<Department> getAllByIdNot(Long id);
}
