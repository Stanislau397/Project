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

    /**
     * Empty constructor for Comment object
     * with no parameters
     */
    public Comment() {

    }

    /**
     * Constructor for Comment object
     * @param commentId long value of commentId
     * @param text String value of commentText
     * @param userName String value of userName
     * @param postDate String value of postDate
     * @param commentUpVotes int value of commentUpVotes
     * @param commentDownVotes int value of commentDownVotes
     */
    public Comment(long commentId, String text, String userName, String postDate, int commentUpVotes, int commentDownVotes) {
        this.commentId = commentId;
        this.text = text;
        this.userName = userName;
        this.postDate = postDate;
        this.commentUpVotes = commentUpVotes;
        this.commentDownVotes = commentDownVotes;
    }

    /**
     * Getter method of commentId
     * @return long value of commentId
     */
    public long getCommentId() {
        return commentId;
    }

    /**
     * Setter method of commentId
     * @param commentId long value of commentId
     */
    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    /**
     * Getter method of commentText
     * @return String object of commentText
     */
    public String getText() {
        return text;
    }

    /**
     * Setter method of commentText
     * @param text String object of commentText
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter method of userName
     * @return String object of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method of userName
     * @param userName String object of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method of commentPostDate
     * @return String object of commentPostDate
     */
    public String getPostDate() {
        return postDate;
    }

    /**
     * Setter method of commentPostDate
     * @param postDate String object of commentPostDate
     */
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    /**
     * Getter method of commentUpVotes
     * @return int value of commentUpVotes
     */
    public int getCommentUpVotes() {
        return commentUpVotes;
    }

    /**
     * Setter method of commentUpVotes
     * @param commentUpVotes int value of commentUpVotes
     */
    public void setCommentUpVotes(int commentUpVotes) {
        this.commentUpVotes = commentUpVotes;
    }

    /**
     * Getter method of commentDownVotes
     * @return int value of commentDownVotes
     */
    public int getCommentDownVotes() {
        return commentDownVotes;
    }

    /**
     * Setter method of commentDownVotes
     * @param commentDownVotes int value of commentDownVotes
     */
    public void setCommentDownVotes(int commentDownVotes) {
        this.commentDownVotes = commentDownVotes;
    }

    public int getCountComments() {
        return countComments;
    }

    public void setCountComments(int countComments) {
        this.countComments = countComments;
    }

    /**
     * Getter method of userAvatar
     * @return String object of userAvatar
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * Setter method of userAvatar
     * @param userAvatar String object of userAvatar
     */
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(commentId).append(" ").append(text)
                .append(" ").append(userName).append(" ")
                .append(userAvatar);
        return sb.toString();
    }
}
