package edu.epam.project.parser;

import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class ActorParser {

    private static final String REGEX_DELIMITER = ", ";

    public List<Actor> parseActor(String actors) {
        String[] actorsArray = actors.split(REGEX_DELIMITER);
        List<Actor> actorList = new ArrayList<>();
        for (String actor : actorsArray) {
            String[] result = actor.split(" ");
            String firstName = result[0];
            String lastName = result[1];
            Actor movieActor = new Actor(firstName, lastName);
            actorList.add(movieActor);
        }
        return actorList;
    }

    public Director parseDirector(String input) {
        String[] directorsArray = input.split(" ");
        String firstName = directorsArray[0];
        String lastName = directorsArray[1];
        return new Director(firstName, lastName);
    }
}
