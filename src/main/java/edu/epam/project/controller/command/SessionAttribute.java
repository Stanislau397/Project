package edu.epam.project.controller.command;

public class SessionAttribute {

    private SessionAttribute() {

    }

    public static final String USER_ATTR = "user";
    public static final String DIRECTOR = "director";
    public static final String ACTOR = "actor";
    public static final String USER_NAME = "user_name";
    public static final String USER_ID = "user_id";
    public static final String USER_AVATAR = "user_avatar";
    public static final String MOVIE_TITLE_ATTR = "movie_title_attr";
    public static final String MOVIE_ID_ATTR = "movie_id_attr";

    public static final String CHANGE_PASSWORD = "change_password";

    public static final String GENRE_SUCCESSFULLY_ADDED = "genre_successfully_added";
    public static final String COUNTRY_SUCCESSFULLY_ADDED = "country_successfully_added";
    public static final String DIRECTOR_REMOVED = "director_successfully_removed";
    public static final String ACTOR_REMOVED = "actor_removed";
    public static final String COUNTRY_REMOVED = "country_removed";
    public static final String GENRE_ALREADY_EXISTS = "genre_already_exists";
    public static final String COUNTRY_ALREADY_EXISTS = "country_already_exists";
    public static final String COUNTRY_NAME = "country_name";
    public static final String GENRE_REMOVED = "genre_removed";
    public static final String COMMENT_REMOVED = "comment_removed";
    public static final String COMMENT_EDITED = "comment_edited";
    public static final String AVATAR_EDITED = "avatar_edited";
    public static final String ACTOR_ALREADY_EXISTS = "actor_already_exists";
    public static final String CHANGED_DATA = "changed_data";
    public static final String CHANGED_PICTURE = "changed_picture";
    public static final String CHANGED_TRAILER = "changed_trailer";
    public static final String ERROR = "error";
    public static final String PICTURE_ERROR = "picture_error";
    public static final String TRAILER_ERROR = "trailer_error";
    public static final String DELETE_GENRE_ERROR = "delete_genre_error";
    public static final String BLOCKED_USER = "blocked_user";
    public static final String UNBLOCKED_USER = "unblocked_user";
    public static final String CHANGED_ROLE = "changed_role";
    public static final String INVALID_INPUT = "invalid_input";
    public static final String REGISTRATION_FAILURE = "registration_failure";
}
