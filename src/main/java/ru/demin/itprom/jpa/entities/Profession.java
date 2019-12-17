package ru.demin.itprom.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profession {

    @Id
    @SequenceGenerator(name = "professionSeqGen", sequenceName = "professionSeq")
    @GeneratedValue(generator = "professionSeq")
    private Long id;

    @Column
    private String name;

    @Column
    private String note;

}
