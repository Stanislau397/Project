package edu.epam.project.entity;

public class Comment extends Entity {

    private String text;
    private String userName;
    private String postDate;

    public Comment() {

    }

    public Comment(String text, String userName, String postDate) {
        this.text = text;
        this.userName = userName;
        this.postDate = postDate;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        if (userName != null ? !userName.equals(comment.userName) : comment.userName != null) return false;
        return postDate != null ? postDate.equals(comment.postDate) : comment.postDate == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        return result;
    }
}
