package edu.epam.project.service.impl;

import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.impl.CommentDaoImpl;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.UserService;
import edu.epam.project.validator.MovieValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private CommentDao commentDao = new CommentDaoImpl();
    private UserService userService = new UserServiceImpl();
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public boolean addComment(Comment comment) throws ServiceException, InvalidInputException {
        boolean isAdded = false;
        MovieValidator movieValidator = new MovieValidator();
        User user = comment.getUser();
        Movie movie = comment.getMovie();
        long movieId = movie.getMovieId();
        long userId = user.getUserId();
        String text = comment.getText();
        try {
            boolean userExists = userService.existsById(userId);
            boolean movieExists = movieService.movieExistsById(movieId);
            boolean textValid = movieValidator.isCommentTextValid(text);
            if (textValid && userExists && movieExists) {
                isAdded = commentDao.addComment(comment);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean deleteByCommentIdAndUserId(long commentId, long userId) throws ServiceException {
        boolean isDeleted = false;
        try {
            boolean commentExists = commentExistsByCommentIdAndUserId(commentId, userId);
            if (commentExists) {
                isDeleted = commentDao.deleteByCommentId(commentId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean updateTextByUserIdAndCommentId(long userId, long commentId, String newText) throws ServiceException {
        boolean isUpdated = false;
        MovieValidator movieValidator = new MovieValidator();
        try {
            boolean commentExists = commentExistsByCommentIdAndUserId(commentId, userId);
            boolean newTextValid = movieValidator.isCommentTextValid(newText);
            if (commentExists && newTextValid) {
                isUpdated = commentDao.updateTextByCommentId(commentId, newText);
            }
        } catch (DaoException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean upVoteCommentByCommentIdAndUserId(long commentId, long userId, int upVote) throws ServiceException {
        boolean isUpVoted = false;
        try {
            boolean userExists = userService.existsById(userId);
            boolean commentExists = commentExistsByCommentId(commentId);
            boolean downVoteExists = downVoteExistsByCommentIdAndUserId(commentId, userId);
            boolean upVoteExists = upVoteExistsByCommentIdAndUserId(commentId, userId);
            if (userExists && commentExists && (downVoteExists || upVoteExists)) {
                deleteCommentVoteByCommentIdAndUserId(commentId, userId);
            }
            if (commentExists && userExists && !upVoteExists) {
                isUpVoted = commentDao.upVoteCommentByCommentIdAndUserId(commentId, userId, upVote);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpVoted;
    }

    @Override
    public boolean downVoteCommentByCommentIdAndUserId(long commentId, long userId, int downVote) throws ServiceException {
        boolean isDownVoted = false;
        try {
            boolean userExists = userService.existsById(userId);
            boolean commentExists = commentExistsByCommentId(commentId);
            boolean upVoteExists = upVoteExistsByCommentIdAndUserId(commentId, userId);
            boolean downVoteExists = downVoteExistsByCommentIdAndUserId(commentId, userId);
            if (userExists && commentExists && (upVoteExists || downVoteExists)) {
                deleteCommentVoteByCommentIdAndUserId(commentId, userId);
            }
            if (userExists && commentExists && !downVoteExists) {
                isDownVoted = commentDao.downVoteCommentByCommentIdAndUserId(commentId, userId, downVote);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDownVoted;
    }

    @Override
    public boolean commentExistsByCommentId(long commentId) throws ServiceException {
        boolean commentExists;
        try {
            commentExists = commentDao.commentExistsByCommentId(commentId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return commentExists;
    }

    @Override
    public boolean commentExistsByCommentIdAndUserId(long commentId, long userId) throws ServiceException {
        boolean commentExists;
        try {
            commentExists = commentDao.commentExistsByCommentIdAndUserId(commentId, userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return commentExists;
    }

    @Override
    public boolean upVoteExistsByCommentIdAndUserId(long commentId, long userId) throws ServiceException {
        boolean isUserUpVoted;
        try {
            isUserUpVoted = commentDao.upVoteExistsByCommentIdAndUserId(commentId, userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUserUpVoted;
    }

    @Override
    public boolean downVoteExistsByCommentIdAndUserId(long commentId, long userId) throws ServiceException {
        boolean isUserDownVoted;
        try {
            isUserDownVoted = commentDao.downVoteExistsByCommentIdAndUserId(commentId, userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUserDownVoted;
    }

    @Override
    public boolean deleteCommentVoteByCommentIdAndUserId(long commentId, long userId) throws ServiceException {
        boolean isVoteDeleted;
        try {
            isVoteDeleted = commentDao.deleteCommentVoteByCommentIdAndUserId(commentId, userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isVoteDeleted;
    }

    @Override
    public List<Comment> findCommentsByMovieId(long movieId) throws ServiceException {
        List<Comment> comments = new ArrayList<>();
        try {
            boolean movieExists = movieService.movieExistsById(movieId);
            if (movieExists) {
                comments = commentDao.findCommentsByMovieId(movieId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return comments;
    }

    public int countCommentsByUserId(long userId) throws ServiceException {
        int amountOfComments;
        try {
            amountOfComments = commentDao.countCommentsByUserId(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return amountOfComments;
    }
}
