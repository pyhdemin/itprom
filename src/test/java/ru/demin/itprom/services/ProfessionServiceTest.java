package ru.demin.itprom.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.demin.itprom.exceptions.ProfessionUseInEmloyeeException;
import ru.demin.itprom.jpa.entities.Employee;
import ru.demin.itprom.jpa.entities.Profession;
import ru.demin.itprom.jpa.repositories.ProfessionRepository;
import ru.demin.itprom.services.interfaces.EmployeeService;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProfessionServiceTest {

    @Mock
    private ProfessionRepository professionRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private ProfessionServiceImpl professionService;

    @BeforeEach
    private void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllProfessionsTest() {
        professionService.getAllProfessions();
        verify(professionRepository, times(1)).findAll();
    }

    @Test
    public void  getProfessionByIdTest() {
        professionService.getProfessionById(1L);
        verify(professionRepository, times(1)).findById(1L);
    }

    @Test
    public void  saveProfessionTest() {
        professionService.saveProfession(new Profession());
        verify(professionRepository, times(1)).save(new Profession());
    }

    @Test
    public void  updateProfessionTest() {
        professionService.updateProfession(new Profession());
        verify(professionRepository, times(1)).save(new Profession());
    }

    @Test
    public void  deleteProfessionTest() throws ProfessionUseInEmloyeeException {
        professionService.deleteProfession(1L);
        verify(professionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void  deleteProfessionTestWhenProfessionseInEmployee() {
        when(employeeService.getAllEmployeesByProfessionId(1L)).thenReturn(Arrays.asList(new Employee()));
        assertThrows(ProfessionUseInEmloyeeException.class, () -> professionService.deleteProfession(1L));
    }
}
