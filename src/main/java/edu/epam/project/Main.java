package edu.epam.project;

import edu.epam.project.entity.Movie;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.CommentServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.core.util.ArrayUtils;

import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws ServiceException {
        CommentService commentService = new CommentServiceImpl();
        List<Movie> user = commentService.findCommentsByUserName("Stanislau");
        System.out.println(user.size());
    }
}
