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
    public static final String FIND_ALL_GENRES = "SELECT genres_id, genre_title FROM genres";
    public static final String INSERT_TO_MOVIE_GENRES = "INSERT INTO movie_genres(movie_id, genre_id_fk) VALUES(?,?)";

    public static final String INSERT_TO_ACTOR = "INSERT INTO actors (actor_id, first_name, last_name) VALUES (?,?,?)";
    public static final String DELETE_ACTOR_BY_NAME = "DELETE FROM actors WHERE first_name = (?)";
    public static final String FIND_ACTORS_BY_MOVIE_ID = "SELECT actor_id, first_name, last_name FROM actors JOIN movie_cast " +
            "ON actor_id = actor_id_fk where movie_id = ?";
    public static final String FIND_ACTOR_BY_FIRST_LAST_NAME = "SELECT actor_id, first_name, last_name FROM actors WHERE first_name = (?) AND last_name = (?)";
    public static final String INSERT_ACTOR_TO_MOVIE = "INSERT INTO movie_cast (actor_id_fk, movie_id) VALUES (?,?)";

    public static final String INSERT_TO_DIRECTOR = "INSERT INTO director (director_id, first_name, last_name) VALUES (?,?,?)";
    public static final String FIND_DIRECTORS_BY_MOVIE_ID = "SELECT director_id, first_name, last_name FROM director JOIN movie_direction " +
            "ON director_id = director_id_fk where movie_id_fk = (?)";
    public static final String FIND_DIRECTOR = "SELECT director_id, first_name, last_name FROM director WHERE first_name = (?) AND last_name = (?)";
    public static final String INSERT_TO_MOVIE_DIRECTION = "INSERT INTO movie_direction (movie_id_fk, director_id_fk) VALUES (?,?)";

    public static final String INSERT_TO_MOVIE = "INSERT INTO movies (movie_id, title, release_date, time, country, description, picture) " +
            "VALUES (?,?,?,?,?,?,?)";
    public static final String DELETE_BY_TITLE = "DELETE FROM movies WHERE title = (?)";
    public static final String SELECT_ALL_MOVIES = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), " + 0 + ") AS average FROM movies " +
            "LEFT JOIN rating ON movie_id = movie_id_fk GROUP BY movie_id ORDER BY average DESC";
    public static final String FIND_MOVIE_BY_TITLE = "SELECT movie_id, title, release_date, time, country, description, picture FROM movies " +
            "WHERE title = (?)";
    public static final String FIND_CURRENT_YEAR_MOVIES = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), " + 0 + ") AS average FROM movies " +
            "LEFT JOIN rating ON movie_id = movie_id_fk WHERE YEAR(release_date) = YEAR(CURRENT_TIMESTAMP()) GROUP BY movie_id ORDER BY average DESC";
    public static final String SELECT_MOVIE_BY_YEAR = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), " + 0 + ") AS average FROM movies " +
            "LEFT JOIN rating ON movie_id = movie_id_fk WHERE YEAR(release_date) = (?) GROUP BY movie_id ORDER BY average DESC";
    public static final String SELECT_NEW_MOVIES = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), " + 0 + ") AS average FROM movies " +
            "LEFT JOIN rating ON movie_id = movie_id_fk WHERE YEAR(release_date) = YEAR(CURRENT_TIMESTAMP()) GROUP BY movie_id ORDER BY movie_id DESC";
    public static final String SELECT_MOVIES_BY_GENRE = "SELECT m.movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), " + 0 + ") AS average FROM movies m " +
            "LEFT JOIN rating r ON m.movie_id = r.movie_id_fk LEFT JOIN movie_genres mg ON m.movie_id = mg.movie_id " +
            "LEFT JOIN genres g ON g.genres_id = mg.genre_id_fk WHERE genre_title = (?) GROUP BY mg.movie_id";
    public static final String SELECT_MOVIES_YEARS = "SELECT DISTINCT YEAR(release_date) FROM movies ORDER BY YEAR(release_date) DESC";
    public static final String FIND_MOVIE_BY_ID = "SELECT m.movie_id, title, release_date, time, country, description, picture, IFNULL(avg(user_score), " + 0 + ") AS average, IFNULL(genre_title, 'no-genre') AS genre_title " +
            "FROM movies m LEFT JOIN rating r ON r.movie_id_fk = m.movie_id LEFT JOIN movie_genres g ON m.movie_id = g.movie_id AND g.movie_id = m.movie_id " +
            "LEFT JOIN genres k ON g.genre_id_fk = k.genres_id AND m.movie_id = g.movie_id WHERE m.movie_id = ?";
    public static final String FIND_MOVIE_BY_KEY_WORD = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(avg(user_score), " + 0 + ") AS average FROM movies " +
            "LEFT JOIN rating ON movie_id_fk = movie_id WHERE title LIKE CONCAT( '%',?,'%') GROUP BY movie_id_fk";
    public static final String SELECT_RATED_MOVIES = "SELECT m.user_comment, m.post_date,  r.user_score, title, picture, movie_id FROM movie_comments m , rating r, movies e WHERE" +
            " m.movie_id_fk = r.movie_id_fk AND e.movie_id = r.movie_id_fk and m.user_name_fk = (?) AND r.user_name_fk = (?) GROUP BY movie_id";

    public static final String LEAVE_COMMENT = "INSERT INTO movie_comments(movie_id_fk, user_name_fk, user_comment, post_date) " +
            "VALUES (?,?,?,?)";
    public static final String FIND_COMMENTS_BY_MOVIE_ID = "SELECT user_name_fk, user_comment, post_date FROM movie_comments WHERE movie_id_fk = (?)";
    public static final String REMOVE_COMMENT = "DELETE FROM movie_comments WHERE movie_id_fk = (?) " +
            "AND user_name_fk = (?) AND user_comment = (?)";
    public static final String COUNT_USER_COMMENTS = "SELECT COUNT(user_comment) FROM movie_comments WHERE user_name_fk = (?)";
    public static final String SELECT_USER_COMMENTS = "SELECT user_comment, post_date, movie_id_fk WHERE user_name_fk = (?) " +
            "ORDER BY movie_id_fk";

    public static final String COUNT_AMOUNT_OF_REVIEWS = "SELECT COUNT(user_score) FROM rating WHERE user_name_fk = (?)";
    public static final String COUNT_AVERAGE_RATING_OF_USER = "SELECT AVG(user_score) FROM rating WHERE user_name_fk = (?)";
    public static final String SELECT_LATEST_HIGH_SCORE = "SELECT user_score, title, movie_id FROM rating JOIN movies ON movie_id = movie_id_fk WHERE user_name_fk = (?) AND user_score > 70" +
            " ORDER BY rating_id DESC LIMIT 1";
    public static final String SELECT_LATEST_LOW_SCORE = "SELECT user_score, title, movie_id FROM rating JOIN movies ON movie_id = movie_id_fk WHERE user_name_fk = (?) AND user_score < 50" +
            " ORDER BY rating_id DESC LIMIT 1";
    public static final String RATE_MOVIE = "INSERT INTO rating(movie_id_fk, user_name_fk, user_score) VALUES (?,?,?)";
    public static final String FIND_USER_IN_RATING = "SELECT user_name_fk FROM rating WHERE movie_id_fk = (?) AND user_name_fk = (?)";
    public static final String FIND_MOVIE_SCORE = "SELECT user_score FROM rating WHERE user_name_fk = (?) AND movie_id_fk = (?)";

    private SqlQuery() {

    }
}
