package edu.epam.project.entity;

/**
 * Class represents rating of movie.
 *
 * @author Stanislau Kachan
 */
public class Rating {

    private long ratingId;
    private int score;

    private Rating() {

    }

    public long getRatingId() {
        return ratingId;
    }

    public int getScore() {
        return score;
    }

    public static RatingBuilder newRatingBuilder() {
        return new Rating().new RatingBuilder();
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

    public class RatingBuilder {

        private RatingBuilder() {

        }

        public RatingBuilder withRatingId(long ratingId) {
            Rating.this.ratingId = ratingId;
            return this;
        }

        public RatingBuilder withScore(int score) {
            Rating.this.score = score;
            return this;
        }

        public Rating build() {
            return Rating.this;
        }
    }
}
