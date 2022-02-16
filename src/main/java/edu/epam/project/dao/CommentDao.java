package edu.epam.project.dao;

import edu.epam.project.entity.Comment;
import edu.epam.project.exception.DaoException;

import java.util.List;

public interface CommentDao {

    boolean addComment(Comment comment) throws DaoException; //completed

    boolean deleteByCommentId(long commentId) throws DaoException; //completed

    boolean updateTextByCommentId(long commentId, String newText) throws DaoException; //completed

    boolean upVoteExistsByCommentIdAndUserId(long commentId, long userId) throws DaoException; //completed

    boolean downVoteExistsByCommentIdAndUserId(long commentId, long userId) throws DaoException; //completed

    boolean commentExistsByCommentIdAndUserId(long commentId, long userId) throws DaoException;

    boolean commentExistsByCommentId(long commentId) throws DaoException;

    boolean deleteCommentVoteByCommentIdAndUserId(long commentId, long userId) throws DaoException; //completed

    boolean upVoteCommentByCommentIdAndUserId(long commentId, long userId, int upVote) throws DaoException; //completed

    boolean downVoteCommentByCommentIdAndUserId(long commentId, long userId, int downVote) throws DaoException; //completed

    List<Comment> findCommentsByMovieId(long movieId) throws DaoException; //completed

    int countCommentsByUserId(long userId) throws DaoException; //completed
}
