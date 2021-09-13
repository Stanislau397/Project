package edu.epam.project.controller.command;

public class ErrorMessage {

    private ErrorMessage () {

    }

    public static final String LOGIN_ERROR_MSG = "Your email or password is incorrect!";
    public static final String DELETE_DIRECTOR_ERROR_MSG = "Could not delete director!";
    public static final String DELETE_ACTOR_ERROR_MSG = "Could not delete actor!";
    public static final String DELETE_GENRE_ERROR_MSG = "Could not delete this genre!";
    public static final String DELETE_COUNTRY_ERROR_MSG = "Could not delete this country!";
    public static final String EDIT_ACTOR_ERROR = "Could not edit actor info!";
    public static final String EDIT_DIRECTOR_ERROR = "Could not edit director info!";
    public static final String EDIT_PICTURE_ERROR = "Could not edit picture data!";
    public static final String EDIT_MOVIE_ERROR_MSG = "Could not edit movie info!";
    public static final String EDIT_TRAILER_ERROR_MSG = "Could not edit trailer for movie!";
    public static final String ADD_GENRE_TO_MOVIE_ERROR_MSG = "genre already exists for this movie!";
}
