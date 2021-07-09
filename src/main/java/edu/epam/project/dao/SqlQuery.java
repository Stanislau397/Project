package edu.epam.project.dao;

public class SqlQuery {

    public static final String INSERT_TO_USER = "INSERT INTO users (user_id, user_name, password, email, role, is_active) " +
            "VALUES(?,?,?,?,?,?)";
    public static final String SELECT_USER = "SELECT email, password, role, is_active, user_name FROM users WHERE email = (?) and password = (?)";
    public static final String SELECT_ID_BY_USER_NAME = "SELECT user_id FROM users WHERE user_name = (?)";
    public static final String UPDATE_ROLE_AND_EMAIL = "UPDATE users SET email = (?), role = (?) WHERE user_id = (?)";
    public static final String CHANGE_PASSWORD = "UPDATE users SET password = (?) WHERE user_name = (?) and password = (?)";
    public static final String CHANGE_USER_NAME = "UPDATE users SET user_name = (?) WHERE user_name = (?)";
    public static final String SELECT_USER_BY_USER_NAME = "SELECT user_id, user_name, email, role, is_active FROM users WHERE user_name = (?)";
    public static final String SELECT_ALL_USERS = "SELECT user_id, user_name, email, role, is_active FROM users";
    public static final String SELECT_LATEST_USERS = "SELECT user_id, user_name, email, role, is_active FROM users ORDER BY user_id DESC";
    public static final String UPDATE_USER_STATUS = "Update users SET is_active = (?) WHERE user_name = (?)";
    public static final String UPDATE_USER_ROLE = "Update users SET role = (?) WHERE user_name = (?)";
    public static final String COUNT_USERS = "SELECT COUNT(*) FROM users";

    public static final String INSERT_TO_GENRE = "INSERT INTO genres (genres_id, genre_title) VALUES (?,?)";
    public static final String FIND_ALL_GENRES = "SELECT genres_id, genre_title FROM genres";
    public static final String SELECT_MOVIE_GENRES = "SELECT genre_title, genres_id FROM genres JOIN movie_genres ON genres_id = genre_id_fk WHERE movie_id = (?) " +
            "GROUP BY genres_id";
    public static final String INSERT_TO_MOVIE_GENRES = "INSERT INTO movie_genres(movie_id, genre_id_fk) VALUES(?,?)";
    public static final String DELETE_GENRE_FROM_MOVIE = "DELETE FROM movie_genres WHERE genre_id_fk = (?) AND movie_id = (?)";

    public static final String INSERT_TO_ACTOR = "INSERT INTO actors (actor_id, first_name, last_name, picture, birth_date) VALUES (?,?,?,?,?)";
    public static final String DELETE_ACTOR_BY_NAME = "DELETE FROM actors WHERE first_name = (?)";
    public static final String UPDATE_ACTOR_PICTURE = "UPDATE actors SET picture = (?) WHERE actor_id = (?)";
    public static final String SELECT_ALL_ACTORS = "SELECT actor_id, first_name, last_name FROM actors";
    public static final String SELECT_ACTOR_INFO = "SELECT actor_id, first_name, last_name, picture, IFNULL(height, 0) AS height, IFNULL(birth_date,null) AS birth_date,  (\n" +
            "    (YEAR(CURRENT_DATE) - YEAR(birth_date)) -                             \n" +
            "    (DATE_FORMAT(CURRENT_DATE, '%m%d') < DATE_FORMAT(birth_date, '%m%d'))\n" +
            "  ) AS age FROM actors WHERE actor_id = (?)";
    public static final String UPDATE_ACTOR = "UPDATE actors SET first_name = (?), last_name = (?), birth_date = (?), height = (?) WHERE actor_id = (?)";
    public static final String DELETE_ACTOR = "DELETE FROM actors WHERE actor_id = (?)";
    public static final String FIND_ACTORS_BY_MOVIE_ID = "SELECT actor_id, first_name, last_name FROM actors JOIN movie_cast " +
            "ON actor_id = actor_id_fk where movie_id = ?";
    public static final String SELECT_ACTORS_BY_KEY_WORDS = "SELECT actor_id, first_name, last_name FROM actors WHERE CONCAT(first_name, ' ' , last_name) LIKE CONCAT('%', ? ,'%') GROUP BY actor_id";
    public static final String FIND_ACTOR_BY_FIRST_LAST_NAME = "SELECT actor_id, first_name, last_name FROM actors WHERE first_name = (?) AND last_name = (?)";
    public static final String INSERT_ACTOR_TO_MOVIE_BY_ID = "INSERT INTO movie_cast (actor_id_fk, movie_id) VALUES(?,?)";
    public static final String INSERT_ACTOR_TO_MOVIE = "INSERT INTO movie_cast (actor_id_fk, movie_id) VALUES (?,?)";
    public static final String DELETE_ACTOR_FROM_MOVIE = "DELETE FROM movie_cast WHERE actor_id_fk = (?) AND movie_id = (?)";
    public static final String DELETE_DIRECTOR_FROM_MOVIE = "DELETE FROM movie_direction WHERE director_id_fk = (?) AND movie_id_fk = (?)";

    public static final String INSERT_TO_DIRECTOR = "INSERT INTO director (director_id, first_name, last_name, picture, birth_date) VALUES (?,?,?,?,?)";
    public static final String UPDATE_DIRECTOR_INFO = "UPDATE director SET first_name = (?), last_name = (?), height = (?), birth_date = (?) WHERE director_id = (?)";
    public static final String UPDATE_DIRECTOR_PICTURE = "UPDATE director SET picture = (?) WHERE director_id = (?)";
    public static final String FIND_DIRECTORS_BY_MOVIE_ID = "SELECT director_id, first_name, last_name FROM director JOIN movie_direction " +
            "ON director_id = director_id_fk where movie_id_fk = (?)";
    public static final String FIND_DIRECTOR = "SELECT director_id, first_name, last_name FROM director WHERE first_name = (?) AND last_name = (?)";
    public static final String SELECT_DIRECTOR_INFO = "SELECT director_id, first_name, last_name, picture, IFNULL(height, 0) AS height, IFNULL(birth_date,null) AS birth_date,  (\n" +
            "    (YEAR(CURRENT_DATE) - YEAR(birth_date)) -                             \n" +
            "    (DATE_FORMAT(CURRENT_DATE, '%m%d') < DATE_FORMAT(birth_date, '%m%d'))\n" +
            "  ) AS age FROM director WHERE director_id = (?)";
    public static final String SELECT_DIRECTORS_BY_KEY_WORDS = "SELECT director_id, first_name, last_name FROM director WHERE CONCAT(first_name, ' ' , last_name) LIKE CONCAT('%', ? ,'%') GROUP BY director_id";
    public static final String SELECT_ALL_DIRECTORS = "SELECT director_id, first_name, last_name FROM director";
    public static final String SELECT_MOVIES_FOR_DIRECTOR = "SELECT m.movie_id, m.title, m.release_date, m.picture, IFNULL(AVG(user_score), 0) AS user_score FROM director d\n" +
            "JOIN movie_direction md ON d.director_id = md.director_id_fk\n" +
            "JOIN movies m ON md.movie_id_fk = m.movie_id\n" +
            "LEFT JOIN rating r ON m.movie_id = r.movie_id_fk WHERE md.director_id_fk = (?) GROUP BY r.movie_id_fk";
    public static final String INSERT_DIRECTOR_TO_MOVIE = "INSERT INTO movie_direction (director_id_fk, movie_id_fk) VALUES(?,?)";
    public static final String INSERT_TO_MOVIE_DIRECTION = "INSERT INTO movie_direction (movie_id_fk, director_id_fk) VALUES (?,?)";

    public static final String INSERT_TO_MOVIE = "INSERT INTO movies (movie_id, title, release_date, time, country, description, picture) " +
            "VALUES (?,?,?,?,?,?,?)";
    public static final String UPDATE_MOVIE_POSTER = "UPDATE movies SET picture = (?) WHERE movie_id = (?)";
    public static final String UPDATE_MOVIE_TRAILER = "UPDATE movies SET trailer = (?) WHERE movie_id = (?)";
    public static final String UPDATE_TITLE_RUNTIME_DESCRIPTION_DATE = "UPDATE movies SET title = (?), time = (?), release_date = (?), description = (?) WHERE movie_id = (?)";
    public static final String COUNT_MOVIES = "SELECT COUNT(movie_id) FROM movies";
    public static final String SELECT_MOVIE_POSTER = "SELECT picture FROM movies WHERE movie_id = (?)";
    public static final String DELETE_BY_TITLE = "DELETE FROM movies WHERE title = (?)";
    public static final String SELECT_ALL_MOVIES = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), " + 0 + ") AS average FROM movies " +
            "LEFT JOIN rating ON movie_id = movie_id_fk GROUP BY movie_id ORDER BY average DESC";
    public static final String FIND_MOVIE_BY_TITLE = "SELECT movie_id, title, release_date, time, country, description, picture FROM movies " +
            "WHERE title = (?)";
    public static final String SELECT_MOVIES_FOR_ACTOR = "SELECT first_name, last_name, m.movie_id, m.release_date, m.picture, m.title, IFNULL(AVG(user_score), 0) AS user_score FROM actors a \n" +
            "JOIN movie_cast mc ON a.actor_id = mc.actor_id_fk\n" +
            "JOIN movies m ON mc.movie_id = m.movie_id\n" +
            "LEFT JOIN rating r ON m.movie_id = r.movie_id_fk WHERE mc.actor_id_fk = (?) GROUP BY m.movie_id";
    public static final String FIND_CURRENT_YEAR_MOVIES = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), " + 0 + ") AS average FROM movies " +
            "LEFT JOIN rating ON movie_id = movie_id_fk WHERE YEAR(release_date) = YEAR(CURRENT_TIMESTAMP()) AND DATE(release_date) <= DATE(NOW()) GROUP BY movie_id ORDER BY average DESC";
    public static final String SELECT_MOVIE_BY_YEAR = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), " + 0 + ") AS average FROM movies " +
            "LEFT JOIN rating ON movie_id = movie_id_fk WHERE YEAR(release_date) = (?) GROUP BY movie_id ORDER BY average DESC";
    public static final String SELECT_NEW_MOVIES = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), 0) AS average FROM movies\n" +
            "LEFT JOIN rating ON movie_id = movie_id_fk WHERE YEAR(release_date) = YEAR(CURRENT_TIMESTAMP()) AND DATE(release_date) <= DATE(NOW()) GROUP BY movie_id ORDER BY movie_id DESC";
    public static final String SELECT_NEW_MOVIES_BY_GENRE = "SELECT m.movie_id, m.title, m.release_date, m.time, m.country, m.description, m.picture, IFNULL(AVG(user_score), 0) AS average FROM movies m\n" +
            "JOIN movie_genres mg ON m.movie_id = mg.movie_id\n" +
            "JOIN genres g ON mg.genre_id_fk = g.genres_id\n" +
            "LEFT JOIN rating r ON mg.movie_id = r.movie_id_fk WHERE YEAR(release_date) = YEAR(CURRENT_TIMESTAMP()) AND DATE(release_date) <= DATE(NOW())\n" +
            "AND g.genre_title = ? GROUP BY m.movie_id ORDER BY m.movie_id DESC";
    public static final String SELECT_UPCOMING_MOVIES = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), 0) AS average FROM movies\n" +
            "LEFT JOIN rating ON movie_id = movie_id_fk WHERE DATE(release_date) > DATE(NOW()) GROUP BY movie_id ORDER BY release_date";
    public static final String SELECT_UPCOMING_MOVIES_BY_GENRE = "SELECT m.movie_id, m.title, m.release_date, m.time, m.country, m.description, m.picture, IFNULL(AVG(user_score), 0) AS average FROM movies m\n" +
            "JOIN movie_genres mg ON m.movie_id = mg.movie_id\n" +
            "JOIN genres g ON mg.genre_id_fk = g.genres_id\n" +
            "LEFT JOIN rating r ON m.movie_id = r.movie_id_fk WHERE DATE(release_date) > DATE(NOW()) AND g.genre_title = (?) GROUP BY m.movie_id ORDER BY release_date";
    public static final String SELECT_MOVIES_BY_GENRE = "SELECT m.movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), " + 0 + ") AS average FROM movies m " +
            "LEFT JOIN rating r ON m.movie_id = r.movie_id_fk LEFT JOIN movie_genres mg ON m.movie_id = mg.movie_id " +
            "LEFT JOIN genres g ON g.genres_id = mg.genre_id_fk WHERE genre_title = (?) GROUP BY mg.movie_id";
    public static final String SELECT_CURRENT_YEAR_MOVIES_BY_GENRE = "SELECT m.movie_id, m.title, m.release_date, m.time, m.country, m.description, m.picture, IFNULL(AVG(user_score), 0) AS average FROM movies m\n" +
            "JOIN movie_genres mg ON m.movie_id = mg.movie_id\n" +
            "JOIN genres g ON mg.genre_id_fk = g.genres_id\n" +
            "LEFT JOIN rating r ON m.movie_id = r.movie_id_fk WHERE YEAR(release_date) = YEAR(CURRENT_TIMESTAMP()) AND DATE(release_date) <= DATE(NOW())\n" +
            "AND g.genre_title = (?) GROUP BY m.movie_id ORDER BY release_date";
    public static final String SELECT_MOVIES_BY_GENRE_AND_YEAR = "SELECT m.movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), 0) AS average FROM movies m\n" +
            "LEFT JOIN rating r ON m.movie_id = r.movie_id_fk LEFT JOIN movie_genres mg ON m.movie_id = mg.movie_id\n" +
            "LEFT JOIN genres g ON g.genres_id = mg.genre_id_fk WHERE genre_title = ifnull(?, genre_title) AND YEAR(release_date) = IFNULL(?, YEAR(release_date)) GROUP BY mg.movie_id";
    public static final String SELECT_MOVIES_YEARS = "SELECT DISTINCT YEAR(release_date) FROM movies ORDER BY YEAR(release_date) DESC";
    public static final String SELECT_BEST_MOVIES_FOR_ACTOR = "SELECT AVG(user_score) AS average, title, picture, m.movie_id FROM movies m JOIN movie_cast mc ON m.movie_id = mc.movie_id\n" +
            "LEFT JOIN rating r ON mc.movie_id = r.movie_id_fk WHERE actor_id_fk = (?) GROUP BY m.movie_id HAVING average >= 60 ORDER BY average DESC";
    public static final String SELECT_BEST_MOVIES_FOR_DIRECTOR = "SELECT AVG(user_score) AS average, title, picture, m.movie_id FROM movies m JOIN movie_direction md ON m.movie_id = md.movie_id_fk\n" +
            "LEFT JOIN rating r ON md.movie_id_fk = r.movie_id_fk WHERE director_id_fk = (?) GROUP BY m.movie_id HAVING average >= 60 ORDER BY average DESC";
    public static final String FIND_MOVIE_BY_ID = "SELECT m.movie_id, title, trailer, release_date, time, country, description, picture, IFNULL(avg(user_score), " + 0 + ") AS average, IFNULL(genre_title, 'no-genre') AS genre_title " +
            "FROM movies m LEFT JOIN rating r ON r.movie_id_fk = m.movie_id LEFT JOIN movie_genres g ON m.movie_id = g.movie_id AND g.movie_id = m.movie_id " +
            "LEFT JOIN genres k ON g.genre_id_fk = k.genres_id AND m.movie_id = g.movie_id WHERE m.movie_id = ?";
    public static final String SELECT_MOST_RATED_MOVIES = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(AVG(user_score), 0) AS average FROM movies " +
            "LEFT JOIN rating ON movie_id = movie_id_fk GROUP BY movie_id HAVING AVG(user_score) >= 80 ORDER BY AVG(user_score) DESC";
    public static final String FIND_MOVIE_BY_KEY_WORD = "SELECT movie_id, title, release_date, time, country, description, picture, IFNULL(avg(user_score), " + 0 + ") AS average FROM movies " +
            "LEFT JOIN rating ON movie_id_fk = movie_id WHERE title LIKE CONCAT( '%',?,'%') GROUP BY movie_id_fk";
    public static final String SELECT_RATED_MOVIES = "SELECT m.user_comment, m.post_date,  r.user_score, title, picture, movie_id FROM movie_comments m , rating r, movies e WHERE" +
            " m.movie_id_fk = r.movie_id_fk AND e.movie_id = r.movie_id_fk and m.user_name_fk = (?) AND r.user_name_fk = (?) GROUP BY movie_id";
    public static final String SELECT_LATEST_MOVIES = "SELECT m.movie_id, m.title, m.release_date, genre_title FROM movies m LEFT JOIN movie_genres mg ON m.movie_id = mg.movie_id\n" +
            "LEFT JOIN genres g ON mg.genre_id_fk = g.genres_id ORDER BY m.movie_id DESC";
    public static final String SELECT_LATEST_REVIEWED_MOVIES = "SELECT m.movie_id, m.title, user_score, user_comment, user_name, r.rating_id FROM movies m\n" +
            "JOIN movie_comments mc ON m.movie_id = mc.movie_id_fk\n" +
            "JOIN rating r ON mc.movie_id_fk = r.movie_id_fk \n" +
            "JOIN users u ON u.user_name = r.user_name_fk GROUP BY r.movie_id_fk ORDER BY r.rating_id DESC";

    public static final String LEAVE_COMMENT = "INSERT INTO movie_comments(movie_id_fk, user_name_fk, user_comment, post_date) " +
            "VALUES (?,?,?,?)";
    public static final String FIND_COMMENTS_BY_MOVIE_ID = "SELECT mv.user_name_fk, mv.comment_id, user_comment, post_date, IFNULL(count(comment_up_vote), 0) AS up_votes, IFNULL(count(comment_down_vote), 0) AS down_votes FROM movie_comments mv\n" +
            "LEFT JOIN comment_votes cv ON mv.comment_id = cv.comment_id_fk WHERE mv.movie_id_fk = (?) group by mv.user_comment";
    public static final String REMOVE_COMMENT = "DELETE FROM movie_comments WHERE movie_id_fk = (?) " +
            "AND user_name_fk = (?) AND user_comment = (?)";
    public static final String UPDATE_COMMENT = "UPDATE movie_comments SET user_comment = (?) WHERE user_comment = (?) AND user_name_fk = (?)";
    public static final String COUNT_USER_COMMENTS = "SELECT COUNT(user_comment) FROM movie_comments WHERE user_name_fk = (?)";
    public static final String SELECT_USER_COMMENTS = "SELECT m.movie_id AS movie_id, mc.comment_id AS comment_id, mc.user_name_fk AS user_name, mc.user_comment, mc.post_date, COUNT(comment_down_vote) AS down_votes, COUNT(comment_up_vote) AS up_votes, m.title FROM movie_comments mc\n" +
            "LEFT JOIN comment_votes cv ON  mc.comment_id = cv.comment_id_fk\n" +
            "LEFT JOIN movies m ON mc.movie_id_fk = m.movie_id WHERE mc.user_name_fk = (?) GROUP BY mc.comment_id";
    public static final String COUNT_UP_VOTES_AND_DOWN_VOTES = "SELECT IFNULL(COUNT(comment_up_vote), 0) AS up_votes, IFNULL(COUNT(comment_down_vote), 0) AS down_votes, user_comment, post_date, movie_id_fk FROM movie_comments\n" +
            "LEFT JOIN comment_votes ON comment_id = (?) WHERE user_name = (?) ORDER BY comment_id_fk";
    public static final String UP_VOTE_COMMENT = "INSERT INTO comment_votes (comment_id_fk, movie_id_fk, user_name_fk, comment_up_vote) VALUES(?,?,?,?)";
    public static final String DOWN_VOTE_COMMENT = "INSERT INTO comment_votes (comment_id_fk, movie_id_fk, user_name_fk, comment_down_vote) VALUES(?,?,?,?)";
    public static final String SELECT_COMMENT_UP_VOTE = "SELECT comment_id_fk, user_name_fk, comment_up_vote FROM comment_votes\n" +
            "WHERE comment_id_fk = (?) AND user_name_fk = (?) AND comment_up_vote = (?)";
    public static final String SELECT_COMMENT_DOWN_VOTE = "SELECT comment_id_fk, user_name_fk, comment_down_vote FROM comment_votes\n" +
            "WHERE comment_id_fk = (?) AND user_name_fk = (?) AND comment_down_vote = (?)";
    public static final String REMOVE_COMMENT_VOTE = "DELETE FROM comment_votes WHERE comment_id_fk = (?) AND user_name_fk = (?)";

    public static final String COUNT_AMOUNT_OF_REVIEWS = "SELECT COUNT(user_score) FROM rating WHERE user_name_fk = (?)";
    public static final String COUNT_AVERAGE_RATING_OF_USER = "SELECT AVG(user_score) FROM rating WHERE user_name_fk = (?)";
    public static final String SELECT_LATEST_HIGH_SCORE = "SELECT user_score, title, movie_id FROM rating JOIN movies ON movie_id = movie_id_fk WHERE user_name_fk = (?) AND user_score > 70" +
            " ORDER BY rating_id DESC LIMIT 1";
    public static final String SELECT_LATEST_LOW_SCORE = "SELECT user_score, title, movie_id FROM rating JOIN movies ON movie_id = movie_id_fk WHERE user_name_fk = (?) AND user_score < 50" +
            " ORDER BY rating_id DESC LIMIT 1";
    public static final String RATE_MOVIE = "INSERT INTO rating(movie_id_fk, user_name_fk, user_score) VALUES (?,?,?)";
    public static final String REMOVE_RATING = "DELETE FROM rating WHERE movie_id_fk = (?) AND user_name_fk = (?)";
    public static final String FIND_USER_IN_RATING = "SELECT user_name_fk FROM rating WHERE movie_id_fk = (?) AND user_name_fk = (?)";
    public static final String FIND_MOVIE_SCORE = "SELECT user_score FROM rating WHERE user_name_fk = (?) AND movie_id_fk = (?)";

    private SqlQuery() {

    }
}
