package edu.epam.project;

import edu.epam.project.entity.Actor;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import edu.epam.project.validator.AccountValidator;

import java.io.File;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws ServiceException, InvalidInputException {
        UserService userService = new UserServiceImpl();
        AccountValidator accountValidator = new AccountValidator();
        boolean existsEmail = userService.existsByEmail("Lancer397@gmail.com");
        boolean existsUserName = userService.existsByUserName("Stanislau");
        boolean accountValid = accountValidator.isValidAccountData("Stalnislau", "Ldkjs397", "Lancer397@gmail.com");
        if (accountValid && !existsEmail && !existsUserName) {
            System.out.println(2);
        }

    }
}
