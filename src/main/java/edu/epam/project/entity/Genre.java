package edu.epam.project.entity;

public class Genre extends Entity {

    private long genreId;
    private String title;


    public Genre() {

    }

    public Genre(long genreId, String title) {
        this.genreId = genreId;
        this.title = title;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (genreId != genre.genreId) return false;
        return title != null ? title.equals(genre.title) : genre.title == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (genreId ^ (genreId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
