package edu.epam.project.service;

import edu.epam.project.entity.Comment;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;

import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment) throws ServiceException, InvalidInputException; //completed

    boolean deleteByCommentIdAndUserId(long commentId, long userId) throws ServiceException; //completed

    boolean updateTextByUserIdAndCommentId(long userId, long commentId, String newText) throws ServiceException; //completed

    boolean upVoteCommentByCommentIdAndUserId(long commentId, long userId, int upVote) throws ServiceException; //completed

    boolean downVoteCommentByCommentIdAndUserId(long commentId, long userId, int downVote) throws ServiceException; //completed

    boolean commentExistsByCommentId(long commentId) throws ServiceException;

    boolean commentExistsByCommentIdAndUserId(long commentId, long userId) throws ServiceException;

    boolean upVoteExistsByCommentIdAndUserId(long commentId, long userId) throws ServiceException; //completed

    boolean downVoteExistsByCommentIdAndUserId(long commentId, long userId) throws ServiceException; //completed

    boolean deleteCommentVoteByCommentIdAndUserId(long commentId, long userId) throws ServiceException; //completed

    List<Comment> findCommentsByMovieId(long movieId) throws ServiceException; //completed

    int countCommentsByUserId(long userId) throws ServiceException; //completed
}
