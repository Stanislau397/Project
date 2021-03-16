package edu.epam.project;

import edu.epam.project.dao.*;
import edu.epam.project.dao.impl.*;
import edu.epam.project.entity.Director;
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
        String str = "Andrea Sooch, Anjelica Huston, Asia Kate Dillon, Courtney Gonzalez, Faith Logan, Halle Berry, Hiroyuki Sanada, Ian McShane, Jason Mantzoukas, Jerome Flynn, John Leguizamo, Kay Day, Keanu Reeves";
        System.out.println(new MovieCastParser().parseActor(str));
        File file = new File("C:/project/src/main/webapp/css/image");
        if (file.exists()) {
            System.out.println(1);
        }
    }
}
