package edu.epam.project.dao;

import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;

import java.sql.Timestamp;
import java.util.List;

public interface CommentDao {

    boolean leaveComment(long movieId, long userId, String comment, Timestamp postDate) throws DaoException;

    boolean deleteCommentById(long commentId) throws DaoException;

    boolean userAlreadyUpVoted(long commentId, long userId, int upVote) throws DaoException;

    boolean userAlreadyDownVoted(long commentId, long userId, int downVote) throws DaoException;

    boolean removeUserVote(long commentId, long userId) throws DaoException;

    Comment findCommentUpVotesAndDownVotes(String userName, long commentId) throws DaoException;

    boolean updateComment(String updatedText, String text, String userName) throws DaoException;

    boolean upVoteComment(long commentId, long userId, int upVote) throws DaoException;

    boolean downVoteComment(long commentId, long userId, int downVote) throws DaoException;

    List<Comment> findCommentsByMovieId(long movieId) throws DaoException;

    List<Movie> findCommentsByUserName(String userName) throws DaoException;

    int countUserCommentsByUserName(String userName) throws DaoException;
}
