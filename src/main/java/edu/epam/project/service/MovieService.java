package edu.epam.project.service;

import edu.epam.project.entity.*;
import edu.epam.project.exception.AlreadyExistsException;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;

import javax.servlet.http.Part;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    boolean add(Movie movie) throws ServiceException, InvalidInputException;

    boolean updateMoviePosterByMovieId(Part file, long movieId) throws ServiceException;

    boolean updateMovieInfoById(String title, int runTime, Date releaseDate, String description, long movie_id) throws ServiceException, InvalidInputException;

    boolean updateMovieTrailerByMovieId(long movieId, String trailer) throws ServiceException;

    boolean movieExistsById(long movieId) throws ServiceException;

    int countMovies() throws ServiceException;

    int countNewestMovies() throws ServiceException;

    int countUserRatedMovies(String userName) throws ServiceException;

    Optional<Movie> findLatestHighRatedMovieForUser(String userName) throws ServiceException;

    Optional<Movie> findLatestLowRatedMovieForUser(String userName) throws ServiceException;

    Optional<Movie> findMoviePosterByMovieId(long movieId) throws ServiceException;

    Optional<Movie> findMovieByTitle(String title) throws ServiceException;

    List<Movie> findAllMovies(int page, int total) throws ServiceException;

    List<Movie> findLatestUploadedMovies() throws ServiceException;

    List<Movie> findLatestReviewedMovies() throws ServiceException;

    List<Movie> findAllCurrentYearMovies() throws ServiceException;

    List<Movie> findMoviesByYear(int year) throws ServiceException;

    List<Movie> findMoviesByGenre(Genre genre) throws ServiceException;

    List<Movie> findMoviesByGenreAndYear(Genre genre, Integer year) throws ServiceException;

    List<Movie> findNewestMovies(int page, int total) throws ServiceException;

    List<Movie> findMoviesWithTrailer() throws ServiceException;

    List<Movie> findUpcomingMovies() throws ServiceException;

    List<Movie> findUpcomingMoviesByGenreTitle(String genreTitle) throws ServiceException;

    List<Movie> findCurrentYearMoviesByGenreTitle(String genreTitle) throws ServiceException;

    List<Movie> findNewestMoviesByGenreTitle(String genreTitle) throws ServiceException;

    List<Movie> findMostRatedMovies() throws ServiceException;

    List<Integer> findAllMovieYears() throws ServiceException;

    List<Movie> findBestMoviesForActorByActorId(long actorId) throws ServiceException;

    List<Movie> findBestMoviesForDirectorByDirectorId(long directorId) throws ServiceException;

    Movie findMovieById(long movieId) throws ServiceException;

    List<Movie> findRatedMoviesByUserName(String userName, int start, int total) throws ServiceException;

    List<Movie> findMoviesByKeyWord(String keyWord) throws ServiceException;

    List<Movie> findMoviesForActorByActorId(long actorId) throws ServiceException;

    boolean addActor(Actor actor) throws ServiceException, InvalidInputException;

    boolean addMultipleActorsToMovieByActorIdsAndMovieId(List<Long> actorIds, long movieId) throws ServiceException;

    boolean addActorToMovieByActorIdAndMovieId(long actorId, long movieId) throws ServiceException;

    boolean updateActorInfoByActorId(long actorId, Actor actor) throws ServiceException;

    boolean uploadActorImageByActorId(long actorId, Part file) throws ServiceException;

    boolean deleteActorByActorId(long actorId) throws ServiceException;

    boolean deleteActorFromMovieByActorIdAndMovieId(long actorId, long movieId) throws ServiceException;

    boolean actorExistsById(long actorId) throws ServiceException;

    boolean actorExistsByFirstnameAndLastname(String firstName, String lastName) throws ServiceException;

    boolean actorExistsInMovieByActorIdAndMovieId(long actorId, long movieId) throws ServiceException;

    Actor findActorById(long actorId) throws ServiceException;

    List<Actor> findActorsByMovieId(long movieId) throws ServiceException;

    List<Actor> findAllActors(int start, int total) throws ServiceException;

    List<Actor> findActorsByKeyWords(String keyWords) throws ServiceException;

    boolean addDirector(Director director) throws ServiceException;

    boolean addDirectorToMovieByDirectorIdAndMovieId(long directorId, long movieId) throws ServiceException;

    boolean updateDirectorById(long directorId, Director director) throws ServiceException;

    boolean deleteDirectorById(long directorId) throws ServiceException;

    boolean deleteDirectorFromMovieByDirectorIdAndMovieId(long directorId, long movieId) throws ServiceException;

    boolean directorExistsByFirstnameAndLastname(String firstname, String lastname) throws ServiceException;

    boolean directorExistsInMovieByDirectorIdAndMovieId(long directorId, long movieId) throws ServiceException;

    boolean directorExistsById(long directorId) throws ServiceException;

    Director findDirectorById(long directorId) throws ServiceException;

    List<Movie> findMoviesForDirector(long directorId) throws ServiceException;

    List<Director> findDirectorsByKeyWords(String keyWords) throws ServiceException;

    List<Director> findDirectorsByMovieId(long movieId) throws ServiceException;

    List<Director> findAllDirectors(int start, int total) throws ServiceException;

    boolean addGenre(Genre genre) throws ServiceException, AlreadyExistsException;

    boolean deleteGenreById(long genreId) throws ServiceException;

    boolean genreExistsByGenreId(long genreId) throws ServiceException;

    boolean genreExistsByGenreTitle(String genreTitle) throws ServiceException;

    boolean genreExistsInMovieByMovieIdAndGenreId(long movieId, long genreId) throws ServiceException;

    boolean addGenreToMovieByGenreIdAndMovieId(long genreId, long movieId) throws ServiceException;

    boolean deleteGenreFromMovieByGenreIdAndMovieId(long genreId, long movieId) throws ServiceException;

    List<Genre> findGenresForMovieByMovieId(long movieId) throws ServiceException;

    List<Genre> findAllGenres() throws ServiceException;

    boolean addCountry(Country country) throws ServiceException;

    boolean addCountryToMovieByMovieIdAndCountryId(long movieId, long countryId) throws ServiceException;

    boolean removeCountryById(long countryId) throws ServiceException;

    boolean removeCountryFromMovieByMovieIdAndCountryId(long movieId, long countryId) throws ServiceException;

    boolean countryExistsByName(String countryName) throws ServiceException;

    boolean countryExistsById(long countryId) throws ServiceException;

    boolean countryExistsInMovieByMovieIdAndCountryId(long movieId, long countryId) throws ServiceException;

    List<Country> findAllCountries() throws ServiceException;

    List<Country> findCountriesForMovieByMovieId(long movieId) throws ServiceException;

    int countGenres() throws ServiceException;

    int countActors() throws ServiceException;

    int countDirectors() throws ServiceException;
}
