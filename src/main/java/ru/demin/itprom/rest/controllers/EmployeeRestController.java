package ru.demin.itprom.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.demin.itprom.rest.converters.EmployeeConverter;
import ru.demin.itprom.rest.represents.EmployeeRepresent;
import ru.demin.itprom.services.interfaces.EmployeeService;

import java.util.List;

@RestController
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeConverter employeeConverter;

    @RequestMapping(value = "employees", method = RequestMethod.GET)
    public List<EmployeeRepresent> getAllEmployees() {
        return employeeConverter.convertToRepresent(employeeService.getAllEmployees());
    }

    @RequestMapping(value = "employees/{id}", method = RequestMethod.GET)
    public EmployeeRepresent getEmployeeById(@PathVariable("id") Long id) {
        return employeeConverter.convertToRepresent(employeeService.getEmployeeById(id));
    }

    @RequestMapping(value = "employees", method = RequestMethod.POST)
    public EmployeeRepresent saveEmployee(@RequestBody EmployeeRepresent employeeRepresent) {
        return employeeConverter.convertToRepresent(employeeService.saveEmployee(employeeConverter.convertToEmployee(employeeRepresent)));
    }

    @RequestMapping(value = "employees/{id}", method = RequestMethod.PUT)
    public EmployeeRepresent updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeRepresent employeeRepresent) {
        employeeRepresent.setId(id.toString());
        return employeeConverter.convertToRepresent(employeeService.updateEmployee(employeeConverter.convertToEmployee(employeeRepresent)));
    }

    @RequestMapping(value = "employees/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
