package edu.epam.project.entity;

/**
 * Class represents movie director
 *
 * @author Stanislau Kachan
 */
public class Director extends Entity {

    private long directorId;
    private String firstName;
    private String lastName;
    private String picture;
    private String birthDate;
    private double height;
    private int age;

    /**
     * Constructor for Director object
     */
    public Director() {

    }

    /**
     * Constructor for Director object
     * with given parameters:
     *
     * @param directorId long value of directorId
     * @param firstName  String object of firstName
     * @param lastName   String object of lastName
     */
    public Director(long directorId, String firstName, String lastName) {
        this.directorId = directorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructor for Director object
     * with given parameters
     *
     * @param firstName String object of firstName
     * @param lastName  String object of lastName
     */
    public Director(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Director(long directorId, String firstName, String lastName, String picture, double height, int age) {
        this.directorId = directorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.height = height;
        this.age = age;
    }

    /**
     * Getter method of directorId
     *
     * @return long value of directorId
     */
    public long getDirectorId() {
        return directorId;
    }

    /**
     * Setter method of directorId
     *
     * @param directorId long value of directorId
     */
    public void setDirectorId(long directorId) {
        this.directorId = directorId;
    }

    /**
     * Getter method of directorFirstName
     *
     * @return String object of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method of directorFirstName
     *
     * @param firstName String object of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method of directorLastName
     *
     * @return String object of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method of directorLastName
     *
     * @param lastName String object of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Director director = (Director) o;

        if (directorId != director.directorId) return false;
        if (Double.compare(director.height, height) != 0) return false;
        if (age != director.age) return false;
        if (firstName != null ? !firstName.equals(director.firstName) : director.firstName != null) return false;
        if (lastName != null ? !lastName.equals(director.lastName) : director.lastName != null) return false;
        if (picture != null ? !picture.equals(director.picture) : director.picture != null) return false;
        return birthDate != null ? birthDate.equals(director.birthDate) : director.birthDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (directorId ^ (directorId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(directorId).append(" ").append(lastName)
                .append(" ").append(firstName);
        return sb.toString();
    }
}
