package edu.epam.project.dao;

import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;

import java.util.List;

public interface CommentDao {

    boolean leaveCommentByUserId(long movieId, String userName, String comment, String postDate) throws DaoException;

    boolean userAlreadyUpVoted(long commentId, String userName, int upVote) throws DaoException;

    boolean userAlreadyDownVoted(long commentId, String userName, int downVote) throws DaoException;

    boolean removeUserVote(long commentId, String userName) throws DaoException;

    Comment findCommentUpVotesAndDownVotes(String userName, long commentId) throws DaoException;

    boolean updateComment(String updatedText, String text, String userName) throws DaoException;

    boolean upVoteComment(long commentId, String user_name, long movieId, int upVote) throws DaoException;

    boolean downVoteComment(long commentId, String user_name, long movieId, int downVote) throws DaoException;

    List<Comment> findCommentsByMovieId(long movieId) throws DaoException;

    List<Movie> findCommentsByUserName(String userName) throws DaoException;

    boolean removeComment(long movieId, String userName, String comment) throws DaoException;

    int countUserCommentsByUserName(String userName) throws DaoException;
}
