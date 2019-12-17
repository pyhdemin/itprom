package ru.demin.itprom.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.demin.itprom.exceptions.DepartmentUseAsParent;
import ru.demin.itprom.exceptions.DepartmentUseInEmployeeException;
import ru.demin.itprom.rest.converters.DepartmentConverter;
import ru.demin.itprom.rest.represents.DepartmentRepresent;
import ru.demin.itprom.services.interfaces.DepartmentService;

import java.util.List;

@RestController
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentConverter departmentConverter;

    @RequestMapping(value = "departments", method = RequestMethod.GET)
    public List<DepartmentRepresent> getAllDepartments() {
        return departmentConverter.convertToRepresent(departmentService.getAllDepartments());
    }

    @RequestMapping(value = "possibleParentDepartments/{id}", method = RequestMethod.GET)
    public List<DepartmentRepresent> getPossibleParentDepartments(@PathVariable("id") Long id) {
        return departmentConverter.convertToRepresent(departmentService.getPossibleParentDepartments(id));
    }

    @RequestMapping(value = "departments/{id}", method = RequestMethod.GET)
    public DepartmentRepresent getDepartmentById(@PathVariable("id") Long id) {
        return departmentConverter.convertToRepresent(departmentService.getDepartmentById(id));
    }

    @RequestMapping(value = "departments", method = RequestMethod.POST)
    public DepartmentRepresent saveDepartment(@RequestBody DepartmentRepresent departmentRepresent) {
        return departmentConverter.convertToRepresent(departmentService.saveDepartment(departmentConverter.convertToDepartment(departmentRepresent)));
    }

    @RequestMapping(value = "departments/{id}", method = RequestMethod.PUT)
    public DepartmentRepresent updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentRepresent departmentRepresent) {
        departmentRepresent.setId(id.toString());
        return departmentConverter.convertToRepresent(departmentService.updateDepartment(departmentConverter.convertToDepartment(departmentRepresent)));
    }

    @RequestMapping(value = "departments/{id}", method = RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable("id") Long id) {
        try {
            departmentService.deleteDepartment(id);
        } catch (DepartmentUseInEmployeeException | DepartmentUseAsParent e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
