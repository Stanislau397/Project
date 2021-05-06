package edu.epam.project.entity;

/**
 * Class represents rating of movie.
 *
 * @author Stanislau Kachan
 */
public class Rating {

    private long ratingId;
    private int score;

    /**
     * Constructor for Rating object
     * with no parameters
     */
    public Rating() {

    }

    /**
     * Constructor for Rating object
     * with ratingId and score
     *
     * @param ratingId long value of ratingId
     * @param score    int value of rating score
     */
    public Rating(long ratingId, int score) {
        this.ratingId = ratingId;
        this.score = score;
    }

    /**
     * Getter method of ratingId
     *
     * @return long value of ratingId
     */
    public long getRatingId() {
        return ratingId;
    }

    /**
     * Setter method of ratingId
     *
     * @param ratingId of Rating object
     */
    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    /**
     * Getter method of ratingScore
     *
     * @return int value of ratingScore
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter method of ratingScore
     *
     * @param score int value of ratingScore
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (ratingId != rating.ratingId) return false;
        return score == rating.score;
    }

    @Override
    public int hashCode() {
        int result = (int) (ratingId ^ (ratingId >>> 32));
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ratingId).append(" ").append(score);
        return sb.toString();
    }
}
