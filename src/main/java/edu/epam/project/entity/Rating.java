package edu.epam.project.entity;

import java.time.LocalDateTime;

/**
 * Class represents rating of movie.
 *
 * @author Stanislau Kachan
 */
public class Rating {

    private long ratingId;
    private int score;
    private User user;
    private Movie movie;
    private LocalDateTime createdAt;

    private Rating() {

    }

    public long getRatingId() {
        return ratingId;
    }

    public int getScore() {
        return score;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
        if (score != rating.score) return false;
        if (user != null ? !user.equals(rating.user) : rating.user != null) return false;
        if (movie != null ? !movie.equals(rating.movie) : rating.movie != null) return false;
        return createdAt != null ? createdAt.equals(rating.createdAt) : rating.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (ratingId ^ (ratingId >>> 32));
        result = 31 * result + score;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
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

        public RatingBuilder withCreatedAt(LocalDateTime createdAt) {
            Rating.this.createdAt = createdAt;
            return this;
        }

        public RatingBuilder withUser(User user) {
            Rating.this.user = user;
            return this;
        }

        public RatingBuilder withMovie(Movie movie) {
            Rating.this.movie =  movie;
            return this;
        }

        public Rating build() {
            return Rating.this;
        }
    }
}
