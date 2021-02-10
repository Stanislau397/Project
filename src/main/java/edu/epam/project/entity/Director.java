package edu.epam.project.entity;

public class Director extends Entity {

    private long directorId;
    private String firstName;
    private String lastName;

    public Director(long directorId, String firstName, String lastName) {
        this.directorId = directorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(long directorId) {
        this.directorId = directorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Director director = (Director) o;

        if (directorId != director.directorId) return false;
        if (firstName != null ? !firstName.equals(director.firstName) : director.firstName != null) return false;
        return lastName != null ? lastName.equals(director.lastName) : director.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (directorId ^ (directorId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String space = " ";
        sb.append(firstName).append(space).append(lastName);
        return sb.toString();
    }
}
