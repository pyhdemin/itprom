package ru.demin.itprom.rest.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.demin.itprom.jpa.entities.Department;
import ru.demin.itprom.jpa.entities.Employee;
import ru.demin.itprom.jpa.entities.Profession;
import ru.demin.itprom.rest.represents.EmployeeRepresent;
import ru.demin.itprom.services.interfaces.DepartmentService;
import ru.demin.itprom.services.interfaces.ProfessionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeConverterTest {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private ProfessionService professionService;

    @InjectMocks
    private EmployeeConverter employeeConverter;

    @BeforeEach
    private void init() {
        MockitoAnnotations.initMocks(this);
        when(departmentService.getDepartmentById(1L)).thenReturn(new Department(1L,"IT-отдел","note", new Department(2L, "Администрация", "note", null)));
        when(professionService.getProfessionById(2L)).thenReturn(new Profession(2L,"Инженер", "note"));
    }

    @Test
    public void convertToRepresentTest(){
        Employee employee = new Employee(1L,"Демин Александр Васильевич",
                new Profession(1L,"Инженер","note"),
                new Department(1L,"IT-отдел", "note", new Department(2L, "Администрация", "note", null)),
                "note");
        EmployeeRepresent employeeRepresent = employeeConverter.convertToRepresent(employee);
        assertEquals(employeeRepresent.getId(), "1");
        assertEquals(employeeRepresent.getFio(), "Демин Александр Васильевич");
        assertEquals(employeeRepresent.getDepartment(), "IT-отдел");
        assertEquals(employeeRepresent.getProfession(), "Инженер");
        assertEquals(employeeRepresent.getNote(), "note");
    }

    @Test
    public void convertToEmployeeTest() {
        EmployeeRepresent employeeRepresent = new EmployeeRepresent("1","Иванов Иван Иванович", "2", "1","note");
        Employee employee = employeeConverter.convertToEmployee(employeeRepresent);
        assertEquals(employee.getId(), 1L);
        assertEquals(employee.getFio(),"Иванов Иван Иванович");
        assertEquals(employee.getDepartment().getId(), 1L);
        assertEquals(employee.getProfession().getId(), 2L);
        assertEquals(employee.getNote(),"note");
        verify(departmentService, times(1)).getDepartmentById(1L);
        verify(professionService, times(1)).getProfessionById(2L);
    }

    @Test
    public void convertToRepresentListTest() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee(1L,"Демин Александр Васильевич",
                new Profession(1L,"Инженер","note"),
                new Department(1L,"IT-отдел", "note", new Department(2L, "Администрация", "note", null)),
                "note");
        employees.add(employee);
        employee = new Employee(2L,"Иванов Иван Иванович",
                new Profession(2L,"Главный бугалтер","note"),
                new Department(2L,"Бухгалтерия", "note", new Department(2L, "Администрация", "note", null)),
                "note");
        employees.add(employee);
        List<EmployeeRepresent> employeeRepresents = employeeConverter.convertToRepresent(employees);
        assertEquals(employeeRepresents.size(), 2);
    }
}
