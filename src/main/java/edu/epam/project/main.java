package edu.epam.project;

import edu.epam.project.dao.*;
import edu.epam.project.dao.impl.*;
import edu.epam.project.entity.*;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.DirectorService;
import edu.epam.project.sevice.GenreService;
import edu.epam.project.sevice.MovieService;
import edu.epam.project.sevice.RatingService;
import edu.epam.project.sevice.impl.DirectorServiceImpl;
import edu.epam.project.sevice.impl.GenreServiceImpl;
import edu.epam.project.sevice.impl.MovieServiceImpl;
import edu.epam.project.sevice.impl.RatingServiceImpl;
import edu.epam.project.util.CurrentDate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class main {

    public static void main(String[] args) throws DaoException, ServiceException {
        GenreDao genreDao = new GenreDaoImpl();
        GenreService genreService = new GenreServiceImpl();
        DirectorService directorService = new DirectorServiceImpl();
        CommentDao commentDao = new CommentDaoImpl();
        RatingService ratingService = new RatingServiceImpl();
        MovieService movieService = new MovieServiceImpl();
        System.out.println(commentDao.countUserCommentsByUserName("Lancer397ldkj"));
    }
}
