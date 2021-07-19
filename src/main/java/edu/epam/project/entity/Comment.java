package edu.epam.project.entity;

public class Comment extends Entity {

    private long commentId;
    private String text;
    private String userName;
    private String userAvatar;
    private String postDate;
    private int commentUpVotes;
    private int commentDownVotes;
    private int countComments;

    public Comment() {

    }

    public Comment(long commentId, String text, String userName, String postDate, int commentUpVotes, int commentDownVotes) {
        this.commentId = commentId;
        this.text = text;
        this.userName = userName;
        this.postDate = postDate;
        this.commentUpVotes = commentUpVotes;
        this.commentDownVotes = commentDownVotes;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public int getCommentUpVotes() {
        return commentUpVotes;
    }

    public void setCommentUpVotes(int commentUpVotes) {
        this.commentUpVotes = commentUpVotes;
    }

    public int getCommentDownVotes() {
        return commentDownVotes;
    }

    public void setCommentDownVotes(int commentDownVotes) {
        this.commentDownVotes = commentDownVotes;
    }

    public int getCountComments() {
        return countComments;
    }

    public void setCountComments(int countComments) {
        this.countComments = countComments;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (commentUpVotes != comment.commentUpVotes) return false;
        if (commentDownVotes != comment.commentDownVotes) return false;
        if (countComments != comment.countComments) return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        if (userName != null ? !userName.equals(comment.userName) : comment.userName != null) return false;
        if (userAvatar != null ? !userAvatar.equals(comment.userAvatar) : comment.userAvatar != null) return false;
        return postDate != null ? postDate.equals(comment.postDate) : comment.postDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userAvatar != null ? userAvatar.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + commentUpVotes;
        result = 31 * result + commentDownVotes;
        result = 31 * result + countComments;
        return result;
    }
}
