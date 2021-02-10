package edu.epam.project.entity;

import java.sql.Date;

public class Movie extends Entity{

    private long movieId;
    private String title;
    private int runTime;
    private String country;
    private String description;
    private Date releaseDate;
    private String picture;
    private Genre genre;

    public Movie() {

    }

    public Movie(long movieId, String title, int runTime, String country, String description, Date releaseDate, String picture) {
        this.movieId = movieId;
        this.title = title;
        this.runTime = runTime;
        this.country = country;
        this.description = description;
        this.releaseDate = releaseDate;
        this.picture = picture;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (movieId != movie.movieId) return false;
        if (runTime != movie.runTime) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (country != null ? !country.equals(movie.country) : movie.country != null) return false;
        if (description != null ? !description.equals(movie.description) : movie.description != null) return false;
        if (releaseDate != null ? !releaseDate.equals(movie.releaseDate) : movie.releaseDate != null) return false;
        return picture != null ? picture.equals(movie.picture) : movie.picture == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (movieId ^ (movieId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + runTime;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }
}
