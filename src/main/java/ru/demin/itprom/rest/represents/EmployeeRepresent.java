package ru.demin.itprom.rest.represents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRepresent {
    private String id;
    private String fio;
    private String profession;
    private String department;
    private String note;
}
