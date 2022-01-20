package edu.epam.project.service.impl;

import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.impl.CommentDaoImpl;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public boolean leaveComment(long userId, long movieId, String comment) throws ServiceException {
        boolean isLeft;
        Date date = new Date();
        Timestamp postDate = new Timestamp(date.getTime());
        try {
            isLeft = commentDao.leaveComment(movieId, userId, comment, postDate);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isLeft;
    }

    @Override
    public boolean deleteCommentById(long commentId) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = commentDao.deleteCommentById(commentId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean updateComment(String updatedText, String text, String userName) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = commentDao.updateComment(updatedText, text, userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean upVoteComment(long commentId, long userId, int upVote) throws ServiceException {
        boolean isUpVoted;
        try {
            if (userAlreadyDownVoted(commentId, userId, 1)) {
                commentDao.removeUserVote(commentId, userId);
            }
            isUpVoted = commentDao.upVoteComment(commentId, userId, upVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpVoted;
    }

    @Override
    public boolean downVoteComment(long commentId, long userId, int downVote) throws ServiceException {
        boolean isDownVoted;
        try {
            if (userAlreadyUpVoted(commentId, userId, 1)) {
                commentDao.removeUserVote(commentId, userId);
            }
            isDownVoted = commentDao.downVoteComment(commentId, userId, downVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDownVoted;
    }

    @Override
    public boolean userAlreadyUpVoted(long commentId, long userId, int upVote) throws ServiceException {
        boolean isUserVoted;
        try {
            isUserVoted = commentDao.userAlreadyUpVoted(commentId, userId, upVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUserVoted;
    }

    @Override
    public boolean userAlreadyDownVoted(long commentId, long userId, int downVote) throws ServiceException {
        boolean isUserDownVoted;
        try {
            isUserDownVoted = commentDao.userAlreadyDownVoted(commentId, userId, downVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUserDownVoted;
    }

    @Override
    public boolean removeUserVote(long commentId, long userId) throws ServiceException {
        boolean isVoteRemoved;
        try {
            isVoteRemoved = commentDao.removeUserVote(commentId, userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isVoteRemoved;
    }

    @Override
    public Comment findCommentUpVotesAndDownVotes(String userName, long commentId) throws ServiceException {
        Comment comment = new Comment();
        try {
            comment = commentDao.findCommentUpVotesAndDownVotes(userName, commentId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return comment;
    }

    @Override
    public List<Comment> findCommentsByMovieId(long movieId) throws ServiceException {
        List<Comment> comments;
        try {
            comments = commentDao.findCommentsByMovieId(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return comments;
    }

    @Override
    public List<Movie> findCommentsByUserName(String userName) throws ServiceException {
        List<Movie> userComments;
        try {
            userComments = commentDao.findCommentsByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return userComments;
    }

    @Override
    public int countUserCommentsByUserName(String userName) throws ServiceException {
        int amountOfComments;
        try {
            amountOfComments = commentDao.countUserCommentsByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return amountOfComments;
    }
}
