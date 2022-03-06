package edu.epam.project.entity;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class represents actor for movie
 *
 * @author Stanislau Kachan
 */
public class Actor extends Entity {

    private long actorId;
    private String firstName;
    private String lastName;
    private String picture;
    private LocalDate birthDate;
    private double height;
    private int age;

    private Actor() {

    }

    public long getActorId() {
        return actorId;
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

    public static ActorBuilder newActorBuilder() {
        return new Actor().new ActorBuilder();
    }

    public class ActorBuilder {

        private ActorBuilder() {

        }

        public ActorBuilder withId(long actorId) {
            Actor.this.actorId = actorId;
            return this;
        }

        public ActorBuilder withFirstname(String firstName) {
            Actor.this.firstName = firstName;
            return this;
        }

        public ActorBuilder withLastname(String lastName) {
            Actor.this.lastName = lastName;
            return this;
        }

        public ActorBuilder withBirthDate(LocalDate birthDate) {
            Actor.this.birthDate = birthDate;
            return this;
        }

        public ActorBuilder withPicture(String picture) {
            Actor.this.picture = picture;
            return this;
        }

        public ActorBuilder withHeight(double height) {
            Actor.this.height = height;
            return this;
        }

        public ActorBuilder withAge(int age) {
            Actor.this.age = age;
            return this;
        }

        public Actor build() {
            return Actor.this;
        }
    }
}
