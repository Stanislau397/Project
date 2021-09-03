package edu.epam.project.controller.command;

public class ErrorMessage {

    private ErrorMessage () {

    }

    public static final String LOGIN_ERROR_MSG = "Your email or password is incorrect!";
    public static final String DELETE_DIRECTOR_ERROR_MSG = "Could not delete director!";
    public static final String DELETE_ACTOR_ERROR_MSG = "Could not delete actor!";
    public static final String EDIT_ACTOR_ERROR = "Could not edit actor info!";
    public static final String EDIT_DIRECTOR_ERROR = "Could not edit director info!";
    public static final String EDIT_PICTURE_ERROR = "Could not edit picture data!";
}
