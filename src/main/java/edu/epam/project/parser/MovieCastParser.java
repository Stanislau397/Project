package edu.epam.project.parser;

import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Director;

import java.util.ArrayList;
import java.util.List;

public class MovieCastParser {

    public static final String REGEX_DELIMITER = ", ";
    public static final String SPACE_REGEX = " ";

    public Director parseDirector(String input) {
        String[] directorArray = input.split(SPACE_REGEX);
        String firstName = directorArray[0];
        String lastName = directorArray[1];
        Director director = new Director(firstName, lastName);
        return director;
    }

    public List<Actor> parseActor(String input) {
        List<Actor> actorList = new ArrayList<>();
        String[] actorsArray = input.split(REGEX_DELIMITER);
        for (int i = 0; i < actorsArray.length; i++) {
            String[] actors = actorsArray[i].split(SPACE_REGEX);
            String firstName = actors[0];
            String lastName = actors[1];
            Actor actor = new Actor(firstName, lastName);
            actorList.add(actor);
        }
        return actorList;
    }
}
