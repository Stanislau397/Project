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

    boolean movieExistsById(long movieId) throws DaoException; //ok

    int countMovies() throws DaoException;

    int countNewestMovies() throws DaoException;

    int countUserRatedMovies(String userName) throws DaoException;

    Optional<Movie> findMoviePosterByMovieId(long movieId) throws DaoException;

    List<Movie> findAll(int page, int total) throws DaoException;

    List<Movie> findAllCurrentYearMovies() throws DaoException;

    List<Integer> findAllMovieYears() throws DaoException;

    List<Movie> findMoviesByYear(int year) throws DaoException;

    List<Movie> findMoviesByGenre(Genre genre) throws DaoException;

    List<Movie> findMoviesByGenreAndYear(Genre genre, Integer year) throws DaoException;

    List<Movie> findNewestMovies(int page, int total) throws DaoException;

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

    List<Movie> findMoviesForDirector(long directorId) throws DaoException;

    List<Movie> findBestMoviesForActorByActorId(long actorId) throws DaoException;

    List<Movie> findBestMoviesForDirectorByDirectorId(long directorId) throws DaoException;

    boolean addActor(Actor actor) throws DaoException; //ok

    boolean addActorToMovieByActorIdAndMovieId(long actorId, long movieId) throws DaoException; //ok

    boolean updateActorById(long actorId, Actor actor) throws DaoException; //ok

    boolean removeActorById(long actorId) throws DaoException; //ok

    boolean removeActorFromMovieByActorIdAndMovieId(long actorId, long movieId) throws DaoException; //ok

    boolean actorExistsByFirstnameAndLastname(String firstName, String lastName) throws DaoException; //ok

    boolean actorExistsInMovieByActorIdAndMovieId(long actorId, long movieId) throws DaoException; //ok

    int countActors() throws DaoException; //ok

    Optional<Actor> findActorById(long actorId) throws DaoException; //ok

    List<Actor> findActorsByMovieId(long movieId) throws DaoException; //ok

    List<Actor> findAllActors(int start, int total) throws DaoException; //ok

    List<Actor> findActorsByKeyWords(String keyWords) throws DaoException; //ok

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

    Optional<Director> findDirectorInfoByDirectorId(long directorId) throws DaoException;

    List<Director> findAllDirectors(int start, int total) throws DaoException;

    List<Director> findDirectorsByKeyWords(String keyWords) throws DaoException;

    List<Director> findDirectorsByMovieId(long movieId) throws DaoException;

    boolean addGenre(Genre genre) throws DaoException; //ok

    boolean removeGenreById(long genreId) throws DaoException; //ok

    boolean addGenreToMovie(long genreId, long movieId) throws DaoException; //ok

    boolean isGenreAlreadyExistsForMovie(long movieId, long genreId) throws DaoException; //ok

    Optional<Genre> findGenreByTitle(String genreTitle) throws DaoException; //ok

    boolean removeGenreFromMovieByMovieIdAndGenreId(long movieId, long genreId) throws DaoException; //ok

    List<Genre> findAllGenres() throws DaoException; //ok

    List<Genre> findGenresForMovieByMovieId(long movieId) throws DaoException; //ok

    int countGenres() throws DaoException; //ok

    int countDirectors() throws DaoException;

    boolean addCountry(Country country) throws DaoException; //ok

    boolean addCountryToMovieByMovieIdAndCountryId(long movieId, long countryId) throws DaoException; //ok

    boolean removeCountryById(long countryId) throws DaoException; //ok

    boolean removeCountryFromMovieByMovieIdAndCountryId(long movieId, long countryId) throws DaoException; //ok

    boolean countryExistsByName(String countryName) throws DaoException; //ok

    boolean countryExistsById(long countryId) throws DaoException; //ok

    boolean countryExistsInMovieByMovieIdAndCountryId(long movieId, long countryId) throws DaoException; //ok

    List<Country> findAllCountries() throws DaoException; //ok

    List<Country> findCountriesForMovieByMovieId(long movieId) throws DaoException; //ok
}
