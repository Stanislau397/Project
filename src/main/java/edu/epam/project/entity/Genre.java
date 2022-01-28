package edu.epam.project.entity;

/**
 * Class represents movieGenre
 *
 * @author Stanislau Kachan
 */
public class Genre extends Entity {

    private long genreId;
    private String genreTitle;

    private Genre() {

    }

    public long getGenreId() {
        return genreId;
    }

    public String getGenreTitle() {
        return genreTitle;
    }

    public static GenreBuilder newGenreBuilder() {
        return new Genre().new GenreBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (genreId != genre.genreId) return false;
        return genreTitle != null ? genreTitle.equals(genre.genreTitle) : genre.genreTitle == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (genreId ^ (genreId >>> 32));
        result = 31 * result + (genreTitle != null ? genreTitle.hashCode() : 0);
        return result;
    }

    public class GenreBuilder {

        private GenreBuilder() {

        }

        public GenreBuilder withId(long genreId) {
            Genre.this.genreId = genreId;
            return this;
        }

        public GenreBuilder withGenreTitle(String genreTitle) {
            Genre.this.genreTitle = genreTitle;
            return this;
        }

        public Genre build() {
            return Genre.this;
        }
    }
}
