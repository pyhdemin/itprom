package ru.demin.itprom.exceptions;

import ru.demin.itprom.jpa.entities.Department;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentUseAsParent extends Exception {
    private List<Department> inUse;
    private String message;

    public DepartmentUseAsParent(String message, List<Department> inUse) {
        this.message = message;
        this.inUse = inUse;
    }

    @Override
    public String getMessage() {
        return message + ": " + inUse.stream().map(department -> department.getName()).collect(Collectors.joining(", "));
    }
}
