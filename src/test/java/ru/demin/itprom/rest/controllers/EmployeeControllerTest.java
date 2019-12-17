package ru.demin.itprom.rest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import ru.demin.itprom.rest.converters.EmployeeConverter;
import ru.demin.itprom.rest.represents.EmployeeRepresent;
import ru.demin.itprom.services.interfaces.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeRestController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeConverter employeeConverter;

    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    public void beforeTest() {
        List<EmployeeRepresent> employeeRepresents = new ArrayList<>();
        EmployeeRepresent employeeRepresent = new EmployeeRepresent("1","Демин","1","1","note");
        employeeRepresents.add(employeeRepresent);
        employeeRepresent = new EmployeeRepresent("2","Демин","1","2","note");
        employeeRepresents.add(employeeRepresent);
        when(employeeConverter.convertToRepresent(new ArrayList<>())).thenReturn(employeeRepresents);
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        ResultActions result = mvc.perform(get("/employees")).andExpect(status().isOk());
        MvcResult mvcResult = result.andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        assertEquals(mvcResult.getResponse().getContentAsString(),"[{\"id\":\"1\",\"fio\":\"Демин\",\"profession\":\"1\",\"department\":\"1\",\"note\":\"note\"},{\"id\":\"2\",\"fio\":\"Демин\",\"profession\":\"1\",\"department\":\"2\",\"note\":\"note\"}]");
    }
}
