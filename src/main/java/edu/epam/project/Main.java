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
import java.sql.Date;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws ServiceException, InvalidInputException {
        MovieService movieService = new MovieServiceImpl();
        Actor actor = movieService.findActorById(12);
        System.out.println(actor.getBirthDate());
    }
}
