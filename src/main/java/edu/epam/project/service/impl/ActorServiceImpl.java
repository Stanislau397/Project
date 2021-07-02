package edu.epam.project.service.impl;

import edu.epam.project.service.ActorService;

import java.time.LocalDate;
import java.time.Period;

public class ActorServiceImpl implements ActorService {


    @Override
    public int calculateAge(LocalDate currentDate, LocalDate birthDate) {
        return Period.between(birthDate, currentDate).getYears();
    }
}
