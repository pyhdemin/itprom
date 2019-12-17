package ru.demin.itprom.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.demin.itprom.jpa.entities.Employee;
import ru.demin.itprom.jpa.repositories.EmployeeRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    private void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest() {
        employeeService.getAllEmployees();
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void getEmployeeByIdTest() {
        employeeService.getEmployeeById(1L);
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    public void saveEmployeeTest(){
        employeeService.saveEmployee(new Employee());
        verify(employeeRepository, times(1)).save(new Employee());
    }
    @Test
    public void updateEmployeeTest(){
        employeeService.updateEmployee(new Employee());
        verify(employeeRepository, times(1)).save(new Employee());
    }
    @Test
    public void getAllEmployeesByProfessionIdTest(){
        employeeService.getAllEmployeesByProfessionId(1L);
        verify(employeeRepository, times(1)).findByProfessionId(1L);
    }
    @Test
    public void getAllEmployeesByDepartmentIdTest(){
        employeeService.getAllEmployeesByDepartmentId(1L);
        verify(employeeRepository, times(1)).findByDepartmentId(1L);
    }
    @Test
    public void deleteEmployeeTest(){
        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
