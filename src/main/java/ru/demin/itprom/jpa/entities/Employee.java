package ru.demin.itprom.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @SequenceGenerator(name = "employeeSeqGen", sequenceName = "employeeSeq")
    @GeneratedValue(generator = "employeeSeq")
    private Long id;

    @Column
    private String fio;

    @ManyToOne
    private Profession profession;

    @ManyToOne
    private Department department;

    @Column
    private String note;

}
