package edu.epam.project;

import edu.epam.project.dao.ActorDao;
import edu.epam.project.dao.GenreDao;
import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.RatingDao;
import edu.epam.project.dao.impl.ActorDaoImpl;
import edu.epam.project.dao.impl.GenreDaoImpl;
import edu.epam.project.dao.impl.MovieDaoImpl;
import edu.epam.project.dao.impl.RatingDaoImpl;
import edu.epam.project.entity.Genre;
import edu.epam.project.entity.Movie;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.DirectorService;
import edu.epam.project.sevice.GenreService;
import edu.epam.project.sevice.RatingService;
import edu.epam.project.sevice.impl.DirectorServiceImpl;
import edu.epam.project.sevice.impl.GenreServiceImpl;
import edu.epam.project.sevice.impl.RatingServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class main {

    public static void main(String[] args) throws DaoException, ServiceException {
        GenreDao genreDao = new GenreDaoImpl();
        GenreService genreService = new GenreServiceImpl();
        DirectorService directorService = new DirectorServiceImpl();
        ActorDao actorDao = new ActorDaoImpl();
        System.out.println(actorDao.findActorsByMovieId(1));
        System.out.println(directorService.findDirectorsByMovieId(1));
    }
}
