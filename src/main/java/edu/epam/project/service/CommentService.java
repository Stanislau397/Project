package edu.epam.project.service;

import edu.epam.project.entity.Comment;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;

import java.util.List;

public interface CommentService {

    boolean add(long userId, long movieId, String text) throws ServiceException, InvalidInputException; //completed

    boolean deleteById(long commentId) throws ServiceException; //completed

    boolean update(long userId, long commentId, String newText) throws ServiceException; //completed

    boolean upVoteComment(long commentId, long userId, int upVote) throws ServiceException; //completed

    boolean downVoteComment(long commentId, long userId, int downVote) throws ServiceException; //completed

    boolean isUserAlreadyUpVoted(long commentId, long userId, int upVote) throws ServiceException; //completed

    boolean isUserAlreadyDownVoted(long commentId, long userId, int downVote) throws ServiceException; //completed

    boolean removeUserVote(long commentId, long userId) throws ServiceException; //completed

    List<Comment> findCommentsByMovieId(long movieId) throws ServiceException; //completed

    int countUserCommentsByUserName(String userName) throws ServiceException; //completed
}
