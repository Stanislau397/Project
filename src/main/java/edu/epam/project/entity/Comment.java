package edu.epam.project.entity;

import java.sql.Timestamp;

/**
 * Class represents comment for movie
 *
 * @author Stanislau Kachan
 */
public class Comment extends Entity {

    private long commentId;
    private int commentUpVotes;
    private int commentDownVotes;
    private String text;
    private Timestamp postDate;
    private User user;

    /**
     * Empty constructor for Comment
     * with no parameters
     */
    public Comment() {

    }

    /**
     * Constructs and initializes a Comment on fields:
     * @param commentId long value of commentId
     * @param text String value of commentText
     * @param user User object of user
     * @param postDate Timestamp object of postDate
     * @param commentUpVotes int value of commentUpVotes
     * @param commentDownVotes int value of commentDownVotes
     */
    public Comment(long commentId, String text, User user, Timestamp postDate, int commentUpVotes, int commentDownVotes) {
        this.commentId = commentId;
        this.text = text;
        this.postDate = postDate;
        this.user = user;
        this.commentUpVotes = commentUpVotes;
        this.commentDownVotes = commentDownVotes;
    }


    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (commentUpVotes != comment.commentUpVotes) return false;
        if (commentDownVotes != comment.commentDownVotes) return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        if (postDate != null ? !postDate.equals(comment.postDate) : comment.postDate != null) return false;
        return user != null ? user.equals(comment.user) : comment.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + commentUpVotes;
        result = 31 * result + commentDownVotes;
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(commentId).append(" ").append(text)
                .append(" ").append(user.getUserName()).append(" ")
                .append(user.getAvatar());
        return sb.toString();
    }
}
