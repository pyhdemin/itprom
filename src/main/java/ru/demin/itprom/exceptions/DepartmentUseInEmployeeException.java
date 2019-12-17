package ru.demin.itprom.exceptions;

import ru.demin.itprom.jpa.entities.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentUseInEmployeeException extends Exception {

    private List<Employee> inUse;
    private String message;

    public DepartmentUseInEmployeeException(String message, List<Employee> inUse) {
        this.message = message;
        this.inUse = inUse;
    }

    @Override
    public String getMessage() {
        return message + ": " + inUse.stream().map(employee -> employee.getFio()).collect(Collectors.joining(", "));
    }
}