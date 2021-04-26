package edu.epam.project;

import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.impl.CommentDaoImpl;
import edu.epam.project.entity.Comment;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.RatingService;
import edu.epam.project.service.impl.RatingServiceImpl;
import org.apache.logging.log4j.core.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws DaoException, ServiceException {
        CommentDao commentDao = new CommentDaoImpl();
        RatingService ratingService = new RatingServiceImpl();
        System.out.println(ratingService.removeRatingByUserNameAndMovieId("Lancer397ldkj", 21));
    }
}
