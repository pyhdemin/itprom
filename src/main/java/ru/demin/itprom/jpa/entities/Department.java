package ru.demin.itprom.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class Department {

    @Id
    @SequenceGenerator(name = "departmentSeqGen", sequenceName = "departmentSeq")
    @GeneratedValue(generator = "departmentSeq")
    private Long id;

    @Column
    private String name;

    @Column
    private String note;

    @ManyToOne
    private Department parent;
}
