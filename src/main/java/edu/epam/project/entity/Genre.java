package edu.epam.project.entity;

/**
 * Class represents movieGenre
 *
 * @author Stanislau Kachan
 */
public class Genre extends Entity {

    private long genreId;
    private String genreTitle;

    /**
     * Constructor for Genre Object
     */
    public Genre() {

    }

    /**
     * Constructor for Genre Object
     * with given parameters:
     *
     * @param genreId    long value of genreId
     * @param genreTitle String object of genreTitle
     */
    public Genre(long genreId, String genreTitle) {
        this.genreId = genreId;
        this.genreTitle = genreTitle;
    }

    /**
     * Getter method of genreId
     *
     * @return long value of genreId
     */
    public long getGenreId() {
        return genreId;
    }

    /**
     * Setter method of genreId
     *
     * @param genreId long value of genreId
     */
    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    /**
     * Getter method of genreTitle
     *
     * @return String object of genreTitle
     */
    public String getGenreTitle() {
        return genreTitle;
    }

    /**
     * Setter method of genreTitle
     *
     * @param genreTitle String object of genreTitle
     */
    public void setGenreTitle(String genreTitle) {
        this.genreTitle = genreTitle;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(genreId).append(" ")
                .append(genreTitle);
        return sb.toString();
    }
}
