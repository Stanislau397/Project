package edu.epam.project.dao;

import edu.epam.project.entity.*;
import edu.epam.project.exception.DaoException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MovieDao {

    boolean add(Movie movie) throws DaoException;

    boolean updateMoviePosterByMovieId(String picturePath, long movieId) throws DaoException;

    boolean updateMovieInfoById(String title, int runTime, Date releaseDate, String description, long movie_id) throws DaoException;

    boolean updateMovieTrailerByMovieId(long movieId, String trailer) throws DaoException;

    int countMovies() throws DaoException;

    int countUserRatedMovies(String userName) throws DaoException;

    Optional<Movie> findMoviePosterByMovieId(long movieId) throws DaoException;

    List<Movie> findAll(int page, int total) throws DaoException;

    List<Movie> findAllCurrentYearMovies() throws DaoException;

    List<Integer> findAllMovieYears() throws DaoException;

    List<Movie> findMoviesByYear(int year) throws DaoException;

    List<Movie> findMoviesByGenre(Genre genre) throws DaoException;

    List<Movie> findMoviesByGenreAndYear(Genre genre, Integer year) throws DaoException;

    List<Genre> findMovieGenresByMovieId(long movieId) throws DaoException;

    List<Movie> findNewestMovies() throws DaoException;

    List<Movie> findUpcomingMovies() throws DaoException;

    List<Movie> findUpcomingMoviesByGenreTitle(String genreTitle) throws DaoException;

    List<Movie> findMoviesWithTrailer() throws DaoException;

    List<Movie> findCurrentYearMoviesByGenreTitle(String genreTitle) throws DaoException;

    List<Movie> findNewestMoviesByGenreTitle(String genreTitle) throws DaoException;

    List<Movie> findMostRatedMovies() throws DaoException;

    List<Movie> findMoviesForActorByActorId(long actorId) throws DaoException;

    Optional<Movie> findLatestHighRatedMovieForUser(String userName) throws DaoException;

    Optional<Movie> findLatestLowRatedMovieForUser(String userName) throws DaoException;

    Optional<Movie> findMovieByTitle(String title) throws DaoException;

    Optional<Movie> findMovieById(long id) throws DaoException;

    List<Movie> findRatedMoviesByUserName(String userName, int start, int total) throws DaoException;

    List<Movie> findMoviesByKeyWord(String keyWord) throws DaoException;

    List<Movie> findLatestUploadedMovies() throws DaoException;

    List<Movie> findLatestReviewedMovies() throws DaoException;

    List<Movie> findBestMoviesForActorByActorId(long actorId) throws DaoException;

    List<Movie> findBestMoviesForDirectorByDirectorId(long directorId) throws DaoException;

    boolean addActor(Actor actor) throws DaoException;

    boolean addActorToMovieById(long actorId, long movieId) throws DaoException;

    boolean addActorToMovieByMovieId(Actor actor, long movieId) throws DaoException;

    boolean updateActorPictureByActorId(long actorId, String picture) throws DaoException;

    boolean updateActorInfoByActorId(long actorId, String firstName, String lastName, Date release_date, double height) throws DaoException;

    boolean updateActorFirstAndLastNameByActorId(String firstName, String lastName, long actorId) throws DaoException;

    boolean removeActorByActorId(long actorId) throws DaoException;

    boolean removeActorFromMovieById(long actorId, long movieId) throws DaoException;

    boolean isActorAlreadyExists(String firstName, String lastName) throws DaoException;

    boolean isActorAlreadyExistsInMovie(long actorId, long movieId) throws DaoException;

    Optional<Actor> findActorInfoByActorId(long actorId) throws DaoException;

    List<Actor> findActorsByMovieId(long movieId) throws DaoException;

    Optional<Actor> findActorByFirstLastName(String firstName, String lastName) throws DaoException;

    Optional<Director> findDirectorInfoByDirectorId(long directorId) throws DaoException;

    List<Actor> findAllActors(int start, int total) throws DaoException;

    List<Actor> findActorsByKeyWords(String keyWords) throws DaoException;

    List<Director> findAllDirectors(int start, int total) throws DaoException;

    List<Director> findDirectorsByKeyWords(String keyWords) throws DaoException;

    boolean addDirector(Director director) throws DaoException;

    boolean addDirectorToMovieById(long directorId, long movieId) throws DaoException;

    boolean updateDirectorPictureByDirectorId(long directorId, String picture) throws DaoException;

    boolean updateDirectorInfoByDirectorId(long directorId, String firstName, String lastName, Date birthDate, double height) throws DaoException;

    boolean removeDirectorById(long directorId) throws DaoException;

    boolean removeDirectorFromMovie(long directorId, long movieId) throws DaoException;

    boolean isDirectorAlreadyExists(Director director) throws DaoException;

    boolean isDirectorAlreadyExistsInMovie(long directorId, long movieId) throws DaoException;

    boolean addDirectorToMovieByMovieId(Director director, long movieId) throws DaoException;

    Optional<Director> findDirectorByFirstLastName(String firstName, String lastName) throws DaoException;

    List<Director> findDirectorsByMovieId(long movieId) throws DaoException;

    List<Movie> findMoviesForDirector(long directorId) throws DaoException;

    boolean addGenre(Genre genre) throws DaoException;

    boolean removeGenreById(long genreId) throws DaoException;

    boolean addGenreToMovie(long genreId, long movieId) throws DaoException;

    boolean isGenreAlreadyExistsForMovie(long movieId, long genreId) throws DaoException;

    Optional<Genre> findGenreByTitle(String genreTitle) throws DaoException;

    boolean removeGenreFromMovieByMovieAndGenreId(long movieId, long genreId) throws DaoException;

    List<Genre> findAllGenres() throws DaoException;

    int countGenres() throws DaoException;

    int countActors() throws DaoException;

    int countDirectors() throws DaoException;

    boolean addCountry(String countryName) throws DaoException;

    boolean removeCountryById(long countryId) throws DaoException;

    boolean addCountryToMovie(long movieId, long countryId) throws DaoException;

    boolean isCountryAlreadyExists(String countryName) throws DaoException;

    boolean isCountryAlreadyExistsInMovie(long movieId, long countryId) throws DaoException;

    List<Country> findAllCountries() throws DaoException;

    List<Country> findCountriesForMovieById(long movieId) throws DaoException;

    boolean removeCountryFromMovie(long movieId, long countryId) throws DaoException;
}
