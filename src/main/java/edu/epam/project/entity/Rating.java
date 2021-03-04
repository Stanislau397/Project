package edu.epam.project.entity;

public class Rating {

    private long movieId;
    private int score;
    private String userName;
    private String movieTitle;

    public Rating(long movieId, int score, String userName, String movieTitle) {
        this.movieId = movieId;
        this.score = score;
        this.userName = userName;
        this.movieTitle = movieTitle;
    }

    public Rating() {

    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (movieId != rating.movieId) return false;
        if (score != rating.score) return false;
        if (userName != null ? !userName.equals(rating.userName) : rating.userName != null) return false;
        return movieTitle != null ? movieTitle.equals(rating.movieTitle) : rating.movieTitle == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (movieId ^ (movieId >>> 32));
        result = 31 * result + score;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (movieTitle != null ? movieTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String space = " ";
        sb.append(movieId).append(space)
                .append(score).append(space).append(userName)
                .append(space).append(movieTitle);
        return sb.toString();
    }
}
