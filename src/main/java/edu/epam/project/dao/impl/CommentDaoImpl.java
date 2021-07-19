package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    private static final Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    @Override
    public boolean leaveCommentByUserId(long movieId, String userName, String comment, String postDate) throws DaoException {
        boolean isLeft;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.LEAVE_COMMENT)) {
            statement.setLong(1, movieId);
            statement.setString(2, userName);
            statement.setString(3, comment);
            statement.setString(4, postDate);
            int update = statement.executeUpdate();
            isLeft = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isLeft;
    }

    @Override
    public Comment findCommentUpVotesAndDownVotes(String userName, long commentId) throws DaoException {
        Comment comment = new Comment();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_UP_VOTES_AND_DOWN_VOTES)) {
            statement.setLong(1, commentId);
            statement.setString(2, userName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int upVotes = resultSet.getInt(TableColumn.COMMENT_UP_VOTES);
            int downVotes = resultSet.getInt(TableColumn.COMMENT_DOWN_VOTES);
            comment.setCommentUpVotes(upVotes);
            comment.setCommentDownVotes(downVotes);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return comment;
    }

    @Override
    public boolean updateComment(String updatedText, String text, String userName) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_COMMENT)) {
            statement.setString(1, updatedText);
            statement.setString(2, text);
            statement.setString(3, userName);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean upVoteComment(long commentId, String user_name, long movieId, int upVote) throws DaoException {
        boolean isUpVoted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UP_VOTE_COMMENT)) {
            statement.setLong(1, commentId);
            statement.setLong(2, movieId);
            statement.setString(3, user_name);
            statement.setInt(4, upVote);
            int update = statement.executeUpdate();
            isUpVoted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpVoted;
    }

    @Override
    public boolean downVoteComment(long commentId, String user_name, long movieId, int downVote) throws DaoException {
        boolean isDownVoted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DOWN_VOTE_COMMENT)) {
            statement.setLong(1, commentId);
            statement.setLong(2, movieId);
            statement.setString(3, user_name);
            statement.setInt(4, downVote);
            int update = statement.executeUpdate();
            isDownVoted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDownVoted;
    }

    @Override
    public boolean userAlreadyUpVoted(long commentId, String userName, int upVote) throws DaoException {
        boolean isUserUpVoted = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_COMMENT_UP_VOTE)) {
            statement.setLong(1, commentId);
            statement.setString(2, userName);
            statement.setInt(3, upVote);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isUserUpVoted = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUserUpVoted;
    }

    @Override
    public boolean userAlreadyDownVoted(long commentId, String userName, int downVote) throws DaoException {
        boolean isUserDownVoted = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_COMMENT_DOWN_VOTE)) {
            statement.setLong(1, commentId);
            statement.setString(2, userName);
            statement.setInt(3, downVote);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isUserDownVoted = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUserDownVoted;
    }

    @Override
    public boolean removeUserVote(long commentId, String userName) throws DaoException {
        boolean isVoteRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_COMMENT_VOTE)) {
            statement.setLong(1, commentId);
            statement.setString(2, userName);
            int update = statement.executeUpdate();
            isVoteRemoved = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isVoteRemoved;
    }

    @Override
    public List<Comment> findCommentsByMovieId(long movieId) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_COMMENTS_BY_MOVIE_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                String userName = resultSet.getString(TableColumn.USER_NAME_FK);
                String avatar = resultSet.getString(TableColumn.AVATAR);
                String text = resultSet.getString(TableColumn.COMMENT);
                long commentId = resultSet.getLong(TableColumn.COMMENT_ID);
                String postDate = resultSet.getString(TableColumn.COMMENT_POST_DATE);
                int upVotes = resultSet.getInt(TableColumn.COMMENT_UP_VOTES);
                int downVotes = resultSet.getInt(TableColumn.COMMENT_DOWN_VOTES);
                int countComments = resultSet.getInt(TableColumn.COUNTER);
                comment.setCommentId(commentId);
                comment.setCountComments(countComments);
                comment.setUserName(userName);
                comment.setUserAvatar(avatar);
                comment.setPostDate(postDate);
                comment.setText(text);
                comment.setCommentUpVotes(upVotes);
                comment.setCommentDownVotes(downVotes);
                comments.add(comment);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return comments;
    }

    @Override
    public List<Movie> findCommentsByUserName(String userName) throws DaoException {
        List<Movie> userComments = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_COMMENTS)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Comment comment = new Comment();
                comment.setCommentId(resultSet.getLong(TableColumn.COMMENT_ID));
                comment.setPostDate(resultSet.getString(TableColumn.COMMENT_POST_DATE));
                comment.setText(resultSet.getString(TableColumn.COMMENT));
                comment.setCommentUpVotes(resultSet.getInt(TableColumn.COMMENT_UP_VOTES));
                comment.setCommentDownVotes(resultSet.getInt(TableColumn.COMMENT_DOWN_VOTES));
                comment.setUserName(resultSet.getString(TableColumn.USER_NAME));
                movie.setComment(comment);
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                userComments.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return userComments;
    }

    @Override
    public boolean removeComment(long movieId, String userName, String comment) throws DaoException {
        boolean isRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_COMMENT)) {
            statement.setLong(1, movieId);
            statement.setString(2, userName);
            statement.setString(3, comment);
            int update = statement.executeUpdate();
            isRemoved = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRemoved;
    }

    @Override
    public int countUserCommentsByUserName(String userName) throws DaoException {
        int amountOfComments = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_USER_COMMENTS)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amountOfComments = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return amountOfComments;
    }
}
