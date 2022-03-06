package edu.epam.project;

import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.impl.MovieDaoImpl;
import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.CommentServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import edu.epam.project.validator.AccountValidator;
import edu.epam.project.validator.ServiceValidator;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws ServiceException, InvalidInputException {
        MovieService movieService = new MovieServiceImpl();
        MovieDao movieDao = new MovieDaoImpl();
        String url = "http://localhost:8080/image/avatar/default_avatar.png";
        System.out.println(new File(url).exists());
    }

    public static String reverseString(String original){
        String reversedWords = "";
        String[] words = original.split(" ");
        for(String word : words) {
            StringBuilder builder = new StringBuilder(word);
            reversedWords = reversedWords + builder.reverse() + " ";
        }
        return reversedWords.trim();
    }
}
