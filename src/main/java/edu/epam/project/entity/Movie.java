package edu.epam.project.entity;


import java.sql.Date;

public class Movie extends Entity{

    private long movie_id;
    private String title;
    private int runTime;
    private String country;
    private String description;
    private Date releaseDate;

    public Movie() {

    }

    public Movie(long movie_id, String title, int runTime, String country, String description, Date releaseDate) {
        this.movie_id = movie_id;
        this.title = title;
        this.runTime = runTime;
        this.country = country;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(long movie_id) {
        this.movie_id = movie_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (movie_id != movie.movie_id) return false;
        if (runTime != movie.runTime) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (country != null ? !country.equals(movie.country) : movie.country != null) return false;
        if (description != null ? !description.equals(movie.description) : movie.description != null) return false;
        return releaseDate != null ? releaseDate.equals(movie.releaseDate) : movie.releaseDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (movie_id ^ (movie_id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + runTime;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        return result;
    }
}
