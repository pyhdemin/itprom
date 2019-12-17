package ru.demin.itprom.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.demin.itprom.exceptions.ProfessionUseInEmloyeeException;
import ru.demin.itprom.rest.converters.ProfessionConverter;
import ru.demin.itprom.rest.represents.ProfessionRepresent;
import ru.demin.itprom.services.interfaces.ProfessionService;

import java.util.List;

@RestController
public class ProfessionRestController {

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private ProfessionConverter professionConverter;

    @RequestMapping(value = "professions", method = RequestMethod.GET)
    public List<ProfessionRepresent> getAllProfessions() {
        return professionConverter.convertToRepresent(professionService.getAllProfessions());
    }

    @RequestMapping(value = "professions/{id}", method = RequestMethod.GET)
    public ProfessionRepresent getProfessionById(@PathVariable("id") Long id) {
        return professionConverter.convertToRepresent(professionService.getProfessionById(id));
    }

    @RequestMapping(value = "professions", method = RequestMethod.POST)
    public ProfessionRepresent saveProfession(@RequestBody ProfessionRepresent professionRepresent) {
        return professionConverter.convertToRepresent(professionService.saveProfession(professionConverter.convertToProfession(professionRepresent)));
    }

    @RequestMapping(value = "professions/{id}", method = RequestMethod.PUT)
    public ProfessionRepresent putProfession(@PathVariable("id") Long id, @RequestBody ProfessionRepresent professionRepresent) {
        professionRepresent.setId(id.toString());
        return professionConverter.convertToRepresent(professionService.updateProfession(professionConverter.convertToProfession(professionRepresent)));
    }

    @RequestMapping(value = "professions/{id}", method = RequestMethod.DELETE)
    public void deleteProfession(@PathVariable("id") Long id) {
        try {
            professionService.deleteProfession(id);
        } catch (ProfessionUseInEmloyeeException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
