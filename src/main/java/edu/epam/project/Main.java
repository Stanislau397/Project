package edu.epam.project;

import edu.epam.project.entity.Actor;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;

import java.io.File;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws ServiceException {
        MovieService movieService = new MovieServiceImpl();
        Optional<Actor> actorOptional = movieService.findActorById(3);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            System.out.println(actor.getPicture());
            File file = new File("/image/istockphoto-1016744004-612x612.jpg");
            if (file.exists()) {
                System.out.println("bit=ch");
            }
        }
    }
}
