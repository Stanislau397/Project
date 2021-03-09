package edu.epam.project;

import edu.epam.project.dao.*;
import edu.epam.project.dao.impl.*;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.GenreService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.RatingService;
import edu.epam.project.service.impl.GenreServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.RatingServiceImpl;

public class main {

    public static void main(String[] args) throws DaoException, ServiceException {
        RatingDao ratingDao = new RatingDaoImpl();
        GenreDao genreDao = new GenreDaoImpl();
        GenreService genreService = new GenreServiceImpl();
        DirectorService directorService = new DirectorServiceImpl();
        CommentDao commentDao = new CommentDaoImpl();
        RatingService ratingService = new RatingServiceImpl();
        MovieService movieService = new MovieServiceImpl();
        MovieDao movieDao = new MovieDaoImpl();
        System.out.println(movieDao.findMovieById(1));
    }
}
