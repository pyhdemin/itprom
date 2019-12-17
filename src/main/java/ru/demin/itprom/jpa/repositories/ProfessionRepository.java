package ru.demin.itprom.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demin.itprom.jpa.entities.Profession;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
