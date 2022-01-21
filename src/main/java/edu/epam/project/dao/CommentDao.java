package edu.epam.project.dao;

import edu.epam.project.entity.Comment;
import edu.epam.project.exception.DaoException;

import java.sql.Timestamp;
import java.util.List;

public interface CommentDao {

    boolean add(long movieId, long userId, String text, Timestamp postDate) throws DaoException; //completed

    boolean delete(long commentId) throws DaoException; //completed

    boolean update(long commentId, String newText) throws DaoException; //completed

    boolean isUserAlreadyUpVoted(long commentId, long userId, int upVote) throws DaoException; //completed

    boolean isUserAlreadyDownVoted(long commentId, long userId, int downVote) throws DaoException; //completed

    boolean removeUserVoteByCommentIdAndUserId(long commentId, long userId) throws DaoException; //completed

    boolean upVoteComment(long commentId, long userId, int upVote) throws DaoException; //completed

    boolean downVoteComment(long commentId, long userId, int downVote) throws DaoException; //completed

    List<Comment> findCommentsByMovieId(long movieId) throws DaoException; //completed

    int countUserCommentsByUserName(String userName) throws DaoException; //completed
}
