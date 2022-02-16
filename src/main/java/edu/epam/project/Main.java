package edu.epam.project;

import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Comment;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.CommentServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import edu.epam.project.validator.AccountValidator;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ServiceException, InvalidInputException {
        int a = 2;
        int b = 2;

        if (a == b) {
            System.out.println(2);
        } else if (a==b) {
            System.out.println(2);
        }
    }
}
