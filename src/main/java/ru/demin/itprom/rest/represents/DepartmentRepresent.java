package ru.demin.itprom.rest.represents;

import lombok.Data;

@Data
public class DepartmentRepresent {
    private String id;
    private String name;
    private String note;
    private String parent;
}
