package edu.epam.project.entity;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity {

    private RoleType role;
    private long userId;
    private String userName;
    private String email;
    private boolean isBlocked;

    private List<Comment> comments;

    public User(long userId, String userName, String email, RoleType role, boolean isBlocked) {
        this.role = role;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.isBlocked = isBlocked;
    }

    public User() {

    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (isBlocked != user.isBlocked) return false;
        if (role != user.role) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (isBlocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(userName);
        return sb.toString();
    }
}
