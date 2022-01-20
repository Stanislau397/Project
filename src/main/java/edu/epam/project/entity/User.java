package edu.epam.project.entity;

/**
 * Class represents user
 * @author Stanislau Kachan
 */
public class User extends Entity {

    private long userId;
    private RoleType role;
    private String userName;
    private String email;
    private String avatar;
    private boolean status;

    private User() {

    }

    public long getUserId() {
        return userId;
    }

    public RoleType getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean getStatus() {
        return status;
    }

    public static UserBuilder newUserBuilder() {
        return new User().new UserBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (status != user.status) return false;
        if (role != user.role) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return avatar != null ? avatar.equals(user.avatar) : user.avatar == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    public class UserBuilder {

        private UserBuilder() {

        }

        public UserBuilder withUserId(long userId) {
            User.this.userId = userId;
            return this;
        }

        public UserBuilder withRole(RoleType role) {
            User.this.role = role;
            return this;
        }

        public UserBuilder withUserName(String userName) {
            User.this.userName = userName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            User.this.email = email;
            return this;
        }

        public UserBuilder withAvatar(String avatar) {
            User.this.avatar = avatar;
            return this;
        }

        public UserBuilder withStatus(boolean status) {
            User.this.status = status;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}