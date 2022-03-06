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

    Optional<String> findMoviePosterByTitle(String title) throws DaoException;

    List<Movie> findRatedMoviesByUserName(String userName, int start, int total) throws DaoException;

    List<Movie> findMoviesByKeyWord(String keyWord) throws DaoException;

    List<Movie> findLatestUploadedMovies() throws DaoException;

    List<Movie> findLatestReviewedMovies() throws DaoException;

    List<Movie> findMoviesForDirector(long directorId) throws DaoException;

    List<Movie> findBestMoviesForActorByActorId(long actorId) throws DaoException;

    List<Movie> findBestMoviesForDirectorByDirectorId(long directorId) throws DaoException;

    boolean addActor(Actor actor) throws DaoException; //ok

    boolean addActorToMovieByActorIdAndMovieId(long actorId, long movieId) throws DaoException; //ok

    boolean updateActorInfoById(long actorId, Actor actor) throws DaoException; //ok

    boolean updateActorImageById(long actorId, String imagePath) throws DaoException; //ok

    boolean deleteActorById(long actorId) throws DaoException; //ok

    boolean deleteActorFromMovieByActorIdAndMovieId(long actorId, long movieId) throws DaoException; //ok

    boolean actorExistsById(long actorId) throws DaoException;

    boolean actorExistsByFirstnameAndLastname(String firstName, String lastName) throws DaoException; //ok

    boolean actorExistsInMovieByActorIdAndMovieId(long actorId, long movieId) throws DaoException; //ok

    int countActors() throws DaoException; //ok

    Optional<Actor> findActorById(long actorId) throws DaoException; //ok

    List<Actor> findActorsByMovieId(long movieId) throws DaoException; //ok

    List<Actor> findAllActors(int start, int total) throws DaoException; //ok

    List<Actor> findActorsByKeyWords(String keyWords) throws DaoException; //ok

    boolean addDirector(Director director) throws DaoException; //ok

    boolean addDirectorToMovieByDirectorIdAndMovieId(long directorId, long movieId) throws DaoException;//ok

    boolean updateDirectorById(long directorId, Director director) throws DaoException; //ok

    boolean deleteDirectorById(long directorId) throws DaoException; //ok

    boolean deleteDirectorFromMovieByDirectorIdAndMovieId(long directorId, long movieId) throws DaoException; //ok

    boolean directorExistsByFirstnameAndLastname(String firstname, String lastname) throws DaoException;

    boolean directorExistsInMovieByDirectorIdAndMovieId(long directorId, long movieId) throws DaoException;

    boolean directorExistsById(long directorId) throws DaoException;

    Optional<Director> findDirectorById(long directorId) throws DaoException;

    List<Director> findAllDirectors(int start, int total) throws DaoException;

    List<Director> findDirectorsByKeyWords(String keyWords) throws DaoException;

    List<Director> findDirectorsByMovieId(long movieId) throws DaoException;

    boolean addGenre(Genre genre) throws DaoException; //ok

    boolean deleteGenreById(long genreId) throws DaoException; //ok

    boolean addGenreToMovieByGenreIdAndMovieId(long genreId, long movieId) throws DaoException; //ok

    boolean genreExistsByGenreTitle(String genreTitle) throws DaoException;

    boolean genreExistsByGenreId(long genreId) throws DaoException;

    boolean genreExistsInMovieByMovieIdAndGenreId(long movieId, long genreId) throws DaoException; //ok

    boolean deleteGenreFromMovieByMovieIdAndGenreId(long movieId, long genreId) throws DaoException; //ok

    List<Genre> findAllGenres() throws DaoException; //ok

    List<Genre> findGenresForMovieByMovieId(long movieId) throws DaoException; //ok

    int countGenres() throws DaoException; //ok

    int countDirectors() throws DaoException;

    boolean addCountry(Country country) throws DaoException; //ok

    boolean addCountryToMovieByMovieIdAndCountryId(long movieId, long countryId) throws DaoException; //ok

    boolean deleteCountryById(long countryId) throws DaoException; //ok

    boolean deleteCountryFromMovieByMovieIdAndCountryId(long movieId, long countryId) throws DaoException; //ok

    boolean countryExistsByCountryName(String countryName) throws DaoException; //ok

    boolean countryExistsByCountryId(long countryId) throws DaoException; //ok

    boolean countryExistsInMovieByMovieIdAndCountryId(long movieId, long countryId) throws DaoException; //ok

    List<Country> findAllCountries() throws DaoException; //ok

    List<Country> findCountriesForMovieByMovieId(long movieId) throws DaoException; //ok
}
