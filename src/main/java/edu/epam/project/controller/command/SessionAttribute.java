package edu.epam.project.controller.command;

public class SessionAttribute {

    private SessionAttribute() {

    }

    public static final String ADMIN = "admin";
    public static final String USER = "user";
    public static final String GUEST = "guest";
    public static final String DIRECTOR = "director";
    public static final String ACTOR = "actor";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_ID = "user_id";
    public static final String USER_AVATAR = "user_avatar";

    public static final String CHANGE_PASSWORD = "change_password";

    public static final String GENRE_SUCCESSFULLY_ADDED = "genre_successfully_added";
    public static final String GENRE_ALREADY_EXISTS = "genre_already_exists";
    public static final String GENRE_REMOVED = "genre_removed";
    public static final String COMMENT_REMOVED = "comment_removed";
    public static final String COMMENT_EDITED = "comment_edited";
    public static final String AVATAR_EDITED = "avatar_edited";
    public static final String PASSWORD_EDITED = "password_edited";
    public static final String ACTOR_ALREADY_EXISTS = "actor_already_exists";
}
