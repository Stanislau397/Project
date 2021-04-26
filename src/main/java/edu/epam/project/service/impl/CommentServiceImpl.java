package edu.epam.project.service.impl;

import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.impl.CommentDaoImpl;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.util.CurrentDate;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public boolean leaveComment(String userName, long movieId, String comment) throws ServiceException {
        CurrentDate currentDate = new CurrentDate();
        Date date = new Date();
        String postDate = currentDate.getCurrentDate(date);
        boolean isLeft;
        try {
            isLeft = commentDao.leaveCommentByUserId(movieId, userName, comment, postDate);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isLeft;
    }

    @Override
    public boolean upVoteComment(long commentId, String user_name, long movieId, int upVote) throws ServiceException {
        boolean isUpVoted;
        try {
            if (userAlreadyDownVoted(commentId, user_name, 1)) {
                commentDao.removeUserVote(commentId, user_name);
            }
            isUpVoted = commentDao.upVoteComment(commentId, user_name, movieId, upVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpVoted;
    }

    @Override
    public boolean downVoteComment(long commentId, String user_name, long movieId, int downVote) throws ServiceException {
        boolean isDownVoted;
        try {
            if (userAlreadyUpVoted(commentId, user_name, 1)) {
                commentDao.removeUserVote(commentId, user_name);
            }
            isDownVoted = commentDao.downVoteComment(commentId, user_name, movieId, downVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDownVoted;
    }

    @Override
    public boolean userAlreadyUpVoted(long commentId, String userName, int upVote) throws ServiceException {
        boolean isUserVoted;
        try {
            isUserVoted = commentDao.userAlreadyUpVoted(commentId, userName, upVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUserVoted;
    }

    @Override
    public boolean userAlreadyDownVoted(long commentId, String userName, int downVote) throws ServiceException {
        boolean isUserDownVoted;
        try {
            isUserDownVoted = commentDao.userAlreadyDownVoted(commentId, userName, downVote);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUserDownVoted;
    }

    @Override
    public boolean removeUserVote(long commentId, String userName) throws ServiceException {
        boolean isVoteRemoved;
        try {
            isVoteRemoved = commentDao.removeUserVote(commentId, userName);
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
    public boolean removeComment(long movieId, String userName, String comment) throws ServiceException {
        boolean isRemoved;
        try {
            isRemoved = commentDao.removeComment(movieId, userName, comment);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRemoved;
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
