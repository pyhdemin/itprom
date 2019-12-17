package ru.demin.itprom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demin.itprom.exceptions.ProfessionUseInEmloyeeException;
import ru.demin.itprom.jpa.entities.Employee;
import ru.demin.itprom.jpa.entities.Profession;
import ru.demin.itprom.jpa.repositories.ProfessionRepository;
import ru.demin.itprom.services.interfaces.EmployeeService;
import ru.demin.itprom.services.interfaces.ProfessionService;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<Profession> getAllProfessions() {
        return professionRepository.findAll();
    }

    @Override
    public Profession getProfessionById(Long id) {
        return professionRepository.findById(id).orElse(null);
    }

    @Override
    public Profession saveProfession(Profession profession) {
        return professionRepository.save(profession);
    }

    @Override
    public void deleteProfession(Long id) throws ProfessionUseInEmloyeeException {
        List<Employee> employeesByProfession = employeeService.getAllEmployeesByProfessionId(id);
        if (employeesByProfession.size()>0) throw new ProfessionUseInEmloyeeException("Профессия используется у сотрудников", employeesByProfession);
        professionRepository.deleteById(id);
    }

    @Override
    public Profession updateProfession(Profession profession) {
        return professionRepository.save(profession);
    }

}
