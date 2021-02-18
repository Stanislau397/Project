package edu.epam.project.sevice.impl;

import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.impl.CommentDaoImpl;
import edu.epam.project.entity.Comment;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.CommentService;
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
