package edu.epam.project.service;

import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.ServiceException;

import java.util.List;

public interface CommentService {

    boolean leaveComment(long userId, long movieId, String comment) throws ServiceException;

    boolean deleteCommentById(long commentId) throws ServiceException;

    boolean updateComment(String updatedText, String text, String userName) throws ServiceException;

    boolean upVoteComment(long commentId, long userId, int upVote) throws ServiceException;

    boolean downVoteComment(long commentId, long userId, int downVote) throws ServiceException;

    boolean userAlreadyUpVoted(long commentId, long userId, int upVote) throws ServiceException;

    boolean userAlreadyDownVoted(long commentId, long userId, int downVote) throws ServiceException;

    boolean removeUserVote(long commentId, long userId) throws ServiceException;

    Comment findCommentUpVotesAndDownVotes(String userName, long commentId) throws ServiceException;

    List<Comment> findCommentsByMovieId(long movieId) throws ServiceException;

    List<Movie> findCommentsByUserName(String userName) throws ServiceException;

    int countUserCommentsByUserName(String userName) throws ServiceException;
}
