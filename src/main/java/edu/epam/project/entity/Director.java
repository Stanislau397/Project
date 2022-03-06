package edu.epam.project.entity;

import java.time.LocalDate;
import java.time.Period;

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
    private LocalDate birthDate;
    private double height;
    private int age;

    private Director() {

    }

    public long getDirectorId() {
        return directorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPicture() {
        return picture;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getHeight() {
        return height;
    }

    public int getAge() {
        age = Period.between(birthDate, LocalDate.now()).getYears();
        return age;
    }

    public static DirectorBuilder newDirectorBuilder() {
        return new Director().new DirectorBuilder();
    }

    public class DirectorBuilder {

        private DirectorBuilder() {

        }

        public DirectorBuilder withId(long directorId) {
            Director.this.directorId = directorId;
            return this;
        }

        public DirectorBuilder withFirstname(String firstname) {
            Director.this.firstName = firstname;
            return this;
        }

        public DirectorBuilder withLastname(String lastname) {
            Director.this.lastName = lastname;
            return this;
        }

        public DirectorBuilder withBirthDate(LocalDate birthDate) {
            Director.this.birthDate = birthDate;
            return this;
        }

        public DirectorBuilder withPicture(String picture) {
            Director.this.picture = picture;
            return this;
        }

        public DirectorBuilder withHeight(double height) {
            Director.this.height = height;
            return this;
        }

        public Director build() {
            return Director.this;
        }
    }
}
