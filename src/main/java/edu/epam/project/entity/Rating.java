package edu.epam.project.entity;

public class Rating {

    private int score;

    public Rating(int score) {
        this.score = score;
    }

    public Rating() {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        return score == rating.score;
    }

    @Override
    public int hashCode() {
        return score;
    }
}
