package edu.epam.project.dao;

public class SqlQuery {

    public static final String INSERT_TO_USER = "INSERT INTO users (user_id, user_name, password, email, role, is_active) " +
            "VALUES(?,?,?,?,?,?)";
    public static final String SELECT_USER = "SELECT email, password, role, is_active, user_name FROM users WHERE email = (?) and password = (?)";
    public static final String SELECT_ID_BY_USER_NAME = "SELECT user_id FROM users WHERE user_name = (?)";
    public static final String CHANGE_PASSWORD = "UPDATE users SET password = (?) WHERE user_name = (?) and password = (?)";
    public static final String CHANGE_EMAIL = "UPDATE users SET email = (?) WHERE email = (?)";
    public static final String CHANGE_USER_NAME = "UPDATE users SET user_name = (?) WHERE user_name = (?)";
    public static final String SELECT_ALL_USERS = "SELECT user_id, user_name, password, email, role, is_active FROM users";
    public static final String UPDATE_USER_STATUS = "Update users SET is_active = (?) WHERE user_name = (?)";

    public static final String INSERT_TO_MOVIE = "INSERT TO movies (movie_id, title, release_date, time, country, description) " +
            "VALUES (?,?,?,?,?,?)";
    public static final String DELETE_BY_TITLE = "DELETE FROM movies WHERE title = (?)";
    public static final String SELECT_ALL_MOVIES = "SELECT movie_id, title, release_date, time, country, description FROM movies";
    public static final String FIND_MOVIE_BY_TITLE = "SELECT movie_id, title, release_date, time, country, description FROM movies " +
            "WHERE movie_title = (?)";
}
