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

    private Comment() {

    }

    public long getCommentId() {
        return commentId;
    }

    public int getCommentUpVotes() {
        return commentUpVotes;
    }

    public int getCommentDownVotes() {
        return commentDownVotes;
    }

    public String getText() {
        return text;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public User getUser() {
        return user;
    }

    public static CommentBuilder newCommentBuilder() {
        return new Comment().new CommentBuilder();
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
        result = 31 * result + commentUpVotes;
        result = 31 * result + commentDownVotes;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    public class CommentBuilder {

        private long commentId;
        private int commentUpVotes;
        private int commentDownVotes;
        private String text;
        private Timestamp postDate;
        private User user;

        private CommentBuilder() {

        }

        public CommentBuilder withCommentId(long commentId) {
            Comment.this.commentId = commentId;
            return this;
        }

        public CommentBuilder withUpVotes(int upVotes) {
            Comment.this.commentUpVotes = upVotes;
            return this;
        }

        public CommentBuilder withDownVotes(int downVotes) {
            Comment.this.commentDownVotes = downVotes;
            return this;
        }

        public CommentBuilder withText(String text) {
            Comment.this.text = text;
            return this;
        }

        public CommentBuilder withPostDate(Timestamp postDate) {
            Comment.this.postDate = postDate;
            return this;
        }

        public CommentBuilder withUser(User user) {
            Comment.this.user = user;
            return this;
        }

        public Comment build() {
            return Comment.this;
        }
    }
}
