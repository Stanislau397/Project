package edu.epam.project.entity;

public class Rating {

    private double score;

    public Rating(double score) {
        this.score = score;
    }

    public Rating() {

    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        return Double.compare(rating.score, score) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(score);
        return (int) (temp ^ (temp >>> 32));
    }
}
