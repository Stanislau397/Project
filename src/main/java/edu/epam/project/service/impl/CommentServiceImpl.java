package edu.epam.project.service.impl;

import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.impl.CommentDaoImpl;
import edu.epam.project.entity.Comment;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.validator.MovieValidator;
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
    public boolean add(long userId, long movieId, String text) throws ServiceException, InvalidInputException {
        boolean isLeft = false;
        MovieValidator movieValidator = new MovieValidator();
        Date date = new Date();
        Timestamp postDate = new Timestamp(date.getTime());
        try {
            if (movieValidator.isCommentTextValid(text)) {
                isLeft = commentDao.add(movieId, userId, text, postDate);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isLeft;
    }

    @Override
    public boolean deleteById(long commentId) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = commentDao.delete(commentId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean update(long userId, long commentId, String newText) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = commentDao.update(commentId, newText);
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
            if (isUserAlreadyDownVoted(commentId, userId, 1)) {
                removeUserVote(commentId, userId);
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
            if (isUserAlreadyUpVoted(commentId, userId, 1)) {
                removeUserVote(commentId, userId);
            }
            isDownVoted = commentDao.downVoteComment(commentId, userId, downVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDownVoted;
    }

    @Override
    public boolean isUserAlreadyUpVoted(long commentId, long userId, int upVote) throws ServiceException {
        boolean isUserUpVoted;
        try {
            isUserUpVoted = commentDao.isUserAlreadyUpVoted(commentId, userId, upVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUserUpVoted;
    }

    @Override
    public boolean isUserAlreadyDownVoted(long commentId, long userId, int downVote) throws ServiceException {
        boolean isUserDownVoted;
        try {
            isUserDownVoted = commentDao.isUserAlreadyDownVoted(commentId, userId, downVote);
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
            isVoteRemoved = commentDao.removeUserVoteByCommentIdAndUserId(commentId, userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isVoteRemoved;
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
