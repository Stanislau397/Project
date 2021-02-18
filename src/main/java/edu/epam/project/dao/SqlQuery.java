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

    public static final String INSERT_TO_GENRE = "INSERT INTO genres (genres_id, genre_title) VALUES (?,?)";
    public static final String REMOVE_FROM_GENRE = "DELETE FROM genres WHERE genres_id = (?)";
    public static final String INSERT_TO_MOVIE_GENRE = "INSERT INTO movie_genres (movie_id, genre_id_fk) VALUES (?,?)";
    public static final String FIND_MOVIE_GENRE_BY_MOVIE_ID = "SELECT genres_id, genre_title FROM genres JOIN movie_genres " +
            "ON genres_id = genre_id_fk WHERE movie_id = (?)";

    public static final String INSERT_TO_ACTOR = "INSERT INTO actors (actor_id, first_name, last_name) VALUES (?,?,?)";
    public static final String DELETE_ACTOR_BY_NAME = "DELETE FROM actors WHERE first_name = (?)";
    public static final String FIND_ACTORS_BY_MOVIE_ID = "SELECT actor_id, first_name, last_name FROM actors JOIN movie_cast " +
            "ON actor_id = actor_id_fk where movie_id = ?";

    public static final String INSERT_TO_DIRECTOR = "INSERT INTO director (director_id, first_name, last_name) VALUES (?,?,?)";
    public static final String FIND_DIRECTORS_BY_MOVIE_ID = "SELECT director_id, first_name, last_name FROM director JOIN movie_direction " +
            "ON director_id = director_id_fk where movie_id_fk = (?)";

    public static final String INSERT_TO_MOVIE = "INSERT INTO movies (movie_id, title, release_date, time, country, description, picture) " +
            "VALUES (?,?,?,?,?,?,?)";
    public static final String DELETE_BY_TITLE = "DELETE FROM movies WHERE title = (?)";
    public static final String SELECT_ALL_MOVIES = "SELECT movie_id, title, release_date, time, country, description, picture FROM movies";
    public static final String FIND_MOVIE_BY_TITLE = "SELECT movie_id, title, release_date, time, country, description, picture FROM movies " +
            "WHERE movie_title = (?)";
    public static final String FIND_MOVIE_BY_ID = "SELECT movie_id, title, release_date, time, country, description, picture FROM movies " +
            "WHERE movie_id = (?)";
    public static final String REMOVE_COMMENT = "DELETE FROM movie_comments WHERE movie_id_fk = (?) " +
            "AND user_name_fk = (?) AND comment = (?)";
    public static final String COUNT_USER_COMMENTS = "SELECT COUNT(comment) FROM movie_comments WHERE user_name_fk = (?)";

    public static final String COUNT_AVERAGE_RATING = "SELECT AVG(user_score) FROM rating WHERE movie_id = (?)";
    public static final String RATE_MOVIE = "INSERT INTO rating(movie_id, user_name_fk, user_score) VALUES (?,?,?)";
    public static final String LEAVE_COMMENT = "INSERT INTO movie_comments(movie_id_fk, user_name_fk, comment, post_date) " +
            "VALUES (?,?,?,?)";
    public static final String FIND_COMMENTS_BY_MOVIE_ID = "SELECT user_name_fk, comment, post_date FROM movie_comments WHERE movie_id_fk = (?)";
}
