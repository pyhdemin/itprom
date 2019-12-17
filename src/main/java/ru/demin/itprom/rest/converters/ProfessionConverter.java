package ru.demin.itprom.rest.converters;

import org.springframework.stereotype.Service;
import ru.demin.itprom.jpa.entities.Profession;
import ru.demin.itprom.rest.represents.ProfessionRepresent;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionConverter {

    public ProfessionRepresent convertToRepresent (Profession profession) {
        ProfessionRepresent professionRepresent = new ProfessionRepresent();
        professionRepresent.setId(profession.getId().toString());
        professionRepresent.setName(profession.getName());
        professionRepresent.setNote(profession.getNote());
        return professionRepresent;
    }

    public List<ProfessionRepresent> convertToRepresent (List<Profession> professionList) {
        return professionList.stream().map(profession -> convertToRepresent(profession)).collect(Collectors.toList());
    }

    public Profession convertToProfession (ProfessionRepresent professionRepresent) {
        Profession profession = new Profession();
        if (professionRepresent.getId() != null) profession.setId(Long.parseLong(professionRepresent.getId()));
        profession.setName(professionRepresent.getName());
        profession.setNote(professionRepresent.getNote());
        return profession;
    }
}
