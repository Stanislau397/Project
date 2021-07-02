package edu.epam.project.service;

import java.time.LocalDate;

public interface ActorService {

    int calculateAge(LocalDate currentDate, LocalDate birthDate);
}
