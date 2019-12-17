package ru.demin.itprom.services.interfaces;

import ru.demin.itprom.exceptions.ProfessionUseInEmloyeeException;
import ru.demin.itprom.jpa.entities.Profession;

import java.util.List;

public interface ProfessionService {
    List<Profession> getAllProfessions();
    Profession getProfessionById(Long id);
    Profession saveProfession(Profession profession);
    Profession updateProfession(Profession profession);
    void deleteProfession(Long id) throws ProfessionUseInEmloyeeException;
}
