package edu.epam.project.entity;

/**
 * Class represents user
 *
 * @author Stanislau Kachan
 */
public class User extends Entity {

    private long userId;
    private RoleType role;
    private String userName;
    private String email;
    private boolean isBlocked;

    /**
     * Constructor for User Object
     * with no parameters
     */
    public User() {

    }

    /**
     * Constructor for User Object
     * with given parameters:
     *
     * @param userId    long value of userId
     * @param role      RoleType object of userRole
     * @param userName  String object of userName
     * @param email     String object of userEmail
     * @param isBlocked boolean value of userStatus
     */
    public User(long userId, RoleType role, String userName, String email, boolean isBlocked) {
        this.userId = userId;
        this.role = role;
        this.userName = userName;
        this.email = email;
        this.isBlocked = isBlocked;
    }

    public User(RoleType role, String userName, String email, boolean isBlocked) {
        this.role = role;
        this.userName = userName;
        this.email = email;
        this.isBlocked = isBlocked;
    }

    /**
     * Getter method of userId
     *
     * @return long value of userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Setter method of userId
     *
     * @param userId long value of userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Getter method of userRole
     *
     * @return RoleType Object of userRole
     */
    public RoleType getRole() {
        return role;
    }

    /**
     * Setter method of userRole
     *
     * @param role RoleType object of userRole
     */
    public void setRole(RoleType role) {
        this.role = role;
    }

    /**
     * Getter method of userName
     *
     * @return String object of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method of userName
     *
     * @param userName String object of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method of userEmail
     *
     * @return String object of userEmail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method of userEmail
     *
     * @param email String object of userEmail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method of userStatus
     *
     * @return boolean value of userStatus
     */
    public boolean isBlocked() {
        return isBlocked;
    }

    /**
     * Setter method of userStatus
     *
     * @param blocked boolean value of userStatus
     */
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
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (isBlocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(userId).append(" ").append(role)
                .append(" ").append(userName).append(" ")
                .append(email).append(" ").append(isBlocked);
        return sb.toString();
    }
}
