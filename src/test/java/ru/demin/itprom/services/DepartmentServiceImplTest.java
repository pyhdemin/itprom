package ru.demin.itprom.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.demin.itprom.exceptions.DepartmentUseAsParent;
import ru.demin.itprom.exceptions.DepartmentUseInEmployeeException;
import ru.demin.itprom.jpa.entities.Department;
import ru.demin.itprom.jpa.entities.Employee;
import ru.demin.itprom.jpa.repositories.DepartmentRepository;
import ru.demin.itprom.services.interfaces.EmployeeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    private void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllDepartmentsTest() {
        departmentService.getAllDepartments();
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    public void getDepartmentByIdTest() {
        departmentService.getDepartmentById(1L);
        verify(departmentRepository, times(1)).findById(1L);
    }

    @Test
    public void saveDepartmentTest() {
        departmentService.saveDepartment(new Department());
        verify(departmentRepository, times(1)).save(new Department());
    }

    @Test
    public void updateDepartmentTest() {
        departmentService.updateDepartment(new Department());
        verify(departmentRepository, times(1)).save(new Department());
    }

    @Test
    public void deleteDepartmentTest() throws DepartmentUseInEmployeeException, DepartmentUseAsParent {
        departmentService.deleteDepartment(1L);
        verify(departmentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteDepartmentTestWhenDepartmentUseInEmployee() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());
        when(employeeService.getAllEmployeesByDepartmentId(1L)).thenReturn(employees);
        assertThrows(DepartmentUseInEmployeeException.class, () -> departmentService.deleteDepartment(1L));
    }

    @Test
    public void deleteDepartmentTestWhenDepartmentUseAsParent() {
        when(departmentRepository.getAllByParentId(1L)).thenReturn(Arrays.asList(new Department()));
        assertThrows(DepartmentUseAsParent.class, () -> departmentService.deleteDepartment(1L));
    }

    @Test
    public void getPossibleParentDepartmentsTest() {
        Department dep2 = new Department(2L,"Администрация","",null);
        Department dep3 = new Department(3L,"IT-отдел","", dep2);
        Department dep4 = new Department(4L,"Бухгалтерия","", dep2);
        Department dep5 = new Department(5L,"Отдел продаж","", dep2);
        Department dep6 = new Department(6L,"Отдел продаж подчиненный","", dep5);
        List<Department> departmentsByNotId = new ArrayList<>();
        departmentsByNotId.add(dep2);
        departmentsByNotId.add(dep3);
        departmentsByNotId.add(dep4);
        departmentsByNotId.add(dep5);
        departmentsByNotId.add(dep6);
        when(departmentRepository.getAllByIdNot(1L)).thenReturn(departmentsByNotId);
        List<Department> childDepartmentsForDep1 = new ArrayList<>();
        childDepartmentsForDep1.add(dep5);
        when(departmentRepository.getAllByParentId(1L)).thenReturn(childDepartmentsForDep1);
        List<Department> childDepartmentsForDep5 = new ArrayList<>();
        childDepartmentsForDep5.add(dep6);
        when(departmentRepository.getAllByParentId(5L)).thenReturn(childDepartmentsForDep5);
        List<Department> possibleDepartments = departmentService.getPossibleParentDepartments(1L);
        assertEquals(possibleDepartments.size(), 3);
    }
}
