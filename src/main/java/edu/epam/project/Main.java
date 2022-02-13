package edu.epam.project;

import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.RatingService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.RatingServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws ServiceException {
        LocalDate localDate = LocalDate.of(2021, 1, 11);
        System.out.println(localDate);
        Date date = Date.valueOf(localDate);
        System.out.println(date);
    }
}
