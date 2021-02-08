package edu.epam.project.entity;

public class Actor extends Entity {

    private long actorId;
    private String firstName;
    private String lastName;
    private int age;
    private ActorGender gender;

    public Actor() {

    }

    public Actor(long actorId, String firstName, String lastName, int age, ActorGender gender) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public long getActorId() {
        return actorId;
    }

    public void setActorId(long actorId) {
        this.actorId = actorId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ActorGender getGender() {
        return gender;
    }

    public void setGender(ActorGender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (actorId != actor.actorId) return false;
        if (age != actor.age) return false;
        if (firstName != null ? !firstName.equals(actor.firstName) : actor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(actor.lastName) : actor.lastName != null) return false;
        return gender == actor.gender;
    }

    @Override
    public int hashCode() {
        int result = (int) (actorId ^ (actorId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }
}
