package edu.epam.project.service;

import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Genre;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.ServiceException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    boolean add(Movie movie) throws ServiceException;

    boolean updateMoviePosterByMovieId(String picturePath, long movieId) throws ServiceException;

    boolean updateTitleRunTimeReleaseDateDescriptionByMovieId(String title, int runTime, Date releaseDate, String description, long movie_id) throws ServiceException;

    boolean updateMovieTrailerByMovieId(long movieId, String trailer) throws ServiceException;

    int countMovies() throws ServiceException;

    Optional<Movie> findMoviePosterByMovieId(long movieId) throws ServiceException;

    Optional<Movie> findMovieByTitle(String title) throws ServiceException;

    List<Movie> findAllMovies() throws ServiceException;

    List<Movie> findLatestUploadedMovies() throws ServiceException;

    List<Movie> findLatestReviewedMovies() throws ServiceException;

    List<Movie> findAllCurrentYearMovies() throws ServiceException;

    List<Movie> findMoviesByYear(int year) throws ServiceException;

    List<Movie> findMoviesByGenre(Genre genre) throws ServiceException;

    List<Movie> findMoviesByGenreAndYear(Genre genre, Integer year) throws ServiceException;

    List<Genre> findMovieGenresByMovieId(long movieId) throws ServiceException;

    List<Movie> findNewestMovies() throws ServiceException;

    List<Movie> findUpcomingMovies() throws ServiceException;

    List<Movie> findUpcomingMoviesByGenreTitle(String genreTitle) throws ServiceException;

    List<Movie> findCurrentYearMoviesByGenreTitle(String genreTitle) throws ServiceException;

    List<Movie> findNewestMoviesByGenreTitle(String genreTitle) throws ServiceException;

    List<Movie> findMostRatedMovies() throws ServiceException;

    List<Integer> findAllMovieYears() throws ServiceException;

    List<Movie> findBestMoviesForActorByActorId(long actorId) throws ServiceException;

    List<Movie> findBestMoviesForDirectorByDirectorId(long directorId) throws ServiceException;

    Optional<Movie> findMovieById(long movieId) throws ServiceException;

    List<Movie> findRatedMoviesByUserName(String userName) throws ServiceException;

    List<Movie> findMoviesByKeyWord(String keyWord) throws ServiceException;

    List<Movie> findMoviesForActorByActorId(long actorId) throws ServiceException;

    boolean addActor(Actor actor) throws ServiceException;

    boolean addActorToMovieById(long actorId, long movieId) throws ServiceException;

    boolean addActorToMovieByMovieId(Actor actor, long movieId) throws ServiceException;

    boolean updateActorPictureByActorId(long actorId, String picture) throws ServiceException;

    boolean updateActorInfoByActorId(long actorId, String firstName, String lastName, Date birth_date, double height) throws ServiceException;

    boolean updateActorFirstAndLastNameByActorId(String firstName, String lastName, long actorId) throws ServiceException;

    boolean removeActorByActorId(long actorId) throws ServiceException;

    boolean removeActorFromMovieById(long actorId, long movieId) throws ServiceException;

    boolean isActorAlreadyExists(String firstName, String lastName) throws ServiceException;

    Optional<Actor> findActorInfoByActorId(long actorId) throws ServiceException;

    Optional<Actor> findActorByFirstLastName(String firstName, String lastName) throws ServiceException;

    List<Actor> findActorsByMovieId(long movieId) throws ServiceException;

    List<Actor> findAllActors() throws ServiceException;

    List<Actor> findActorsByKeyWords(String keyWords) throws ServiceException;

    boolean addDirector(Director director) throws ServiceException;

    Optional<Director> findDirectorInfoByDirectorId(long directorId) throws ServiceException;

    boolean addDirectorToMovieById(long directorId, long movieId) throws ServiceException;

    boolean addDirectorToMovieByMovieId(Director director, long movieId) throws ServiceException;

    boolean updateDirectorPictureByDirectorId(long directorId, String picture) throws ServiceException;

    boolean updateDirectorInfoByDirectorId(long directorId, String firstName, String lastName, Date birthDate, double height) throws ServiceException;

    boolean removeDirectorFromMovie(long directorId, long movieId) throws ServiceException;

    Optional<Director> findDirectorByFirstLastName(String firstName, String lastName) throws ServiceException;

    List<Movie> findMoviesForDirector(long directorId) throws ServiceException;

    List<Director> findDirectorsByKeyWords(String keyWords) throws ServiceException;

    boolean isDirectorAlreadyExists(Director director) throws ServiceException;

    List<Director> findDirectorsByMovieId(long movieId) throws ServiceException;

    List<Director> findAllDirectors() throws ServiceException;

    boolean addGenre(Genre genre) throws ServiceException;

    boolean removeGenreById(long genreId) throws ServiceException;

    boolean addGenreToMovie(long genreId, long movieId) throws ServiceException;

    boolean removeGenreFromMovieByMovieAndGenreId(long genreId, long movieId) throws ServiceException;

    List<Genre> findAllGenres() throws ServiceException;
}
