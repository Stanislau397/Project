package edu.epam.project;

import edu.epam.project.dao.*;
import edu.epam.project.dao.impl.*;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Genre;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.parser.MovieCastParser;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;

import java.io.File;
import java.util.Optional;

public class main {

    public static void main(String[] args) throws ServiceException, DaoException {
        MovieService movieService = new MovieServiceImpl();
        MovieDao movieDao = new MovieDaoImpl();
        Optional<Movie> movie = movieService.findMovieById(10);
        Genre genre = new Genre();
        genre.setTitle("Fantasy");
        String str = "Andrea Sooch, Anjelica Huston, Asia Kate Dillon, Courtney Gonzalez, Faith Logan, Halle Berry, Hiroyuki Sanada, Ian McShane, Jason Mantzoukas, Jerome Flynn, John Leguizamo, Kay Day, Keanu Reeves";
        System.out.println(movieDao.findMoviesByGenre(genre));
    }
}
