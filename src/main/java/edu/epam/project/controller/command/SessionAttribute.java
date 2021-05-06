package edu.epam.project.controller.command;

public class SessionAttribute {

    private SessionAttribute() {

    }

    public static final String ADMIN = "admin";
    public static final String USER = "user";
    public static final String GUEST = "guest";
    public static final String DIRECTOR = "director";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_ID = "user_id";

    public static final String SIGN_IN_ERROR = "sign_in_error";
    public static final String SIGN_IN_ERROR_MESSAGE = "Your email or password is incorrect!";
    public static final String CHANGE_PASSWORD = "change_password";
    public static final String ADD_ACTOR_MESSAGE = "add_actor_message";
    public static final String ADD_DIRECTOR_MESSAGE = "add_director_message";

}
