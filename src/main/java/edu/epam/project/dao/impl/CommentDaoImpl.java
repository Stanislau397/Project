package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    private static final Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    @Override
    public boolean add(long movieId, long userId, String text, Timestamp postDate) throws DaoException {
        boolean isLeft;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_COMMENT)) {
            statement.setLong(1, movieId);
            statement.setLong(2, userId);
            statement.setString(3, text);
            statement.setTimestamp(4, postDate);
            int update = statement.executeUpdate();
            isLeft = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isLeft;
    }

    @Override
    public boolean delete(long commentId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_COMMENT_BY_ID)) {
            statement.setLong(1, commentId);
            int update = statement.executeUpdate();
            isDeleted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean update(long commentId, String newText) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_COMMENT)) {
            statement.setString(1, newText);
            statement.setLong(2, commentId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean upVoteComment(long commentId, long userId, int upVote) throws DaoException {
        boolean isUpVoted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UP_VOTE_COMMENT)) {
            statement.setLong(1, commentId);
            statement.setLong(2, userId);
            statement.setInt(3, upVote);
            int update = statement.executeUpdate();
            isUpVoted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpVoted;
    }

    @Override
    public boolean downVoteComment(long commentId, long userId, int downVote) throws DaoException {
        boolean isDownVoted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DOWN_VOTE_COMMENT)) {
            statement.setLong(1, commentId);
            statement.setLong(2, userId);
            statement.setInt(3, downVote);
            int update = statement.executeUpdate();
            isDownVoted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDownVoted;
    }

    @Override
    public boolean isUserAlreadyUpVoted(long commentId, long userId, int upVote) throws DaoException {
        boolean isUserUpVoted = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_COMMENT_UP_VOTE)) {
            statement.setLong(1, commentId);
            statement.setLong(2, userId);
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
    public boolean isUserAlreadyDownVoted(long commentId, long userId, int downVote) throws DaoException {
        boolean isUserDownVoted = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_COMMENT_DOWN_VOTE)) {
            statement.setLong(1, commentId);
            statement.setLong(2, userId);
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
    public boolean removeUserVoteByCommentIdAndUserId(long commentId, long userId) throws DaoException {
        boolean isVoteRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_COMMENT_VOTE)) {
            statement.setLong(1, commentId);
            statement.setLong(2, userId);
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
                User user = User.newUserBuilder()
                        .withUserName(resultSet.getString(TableColumn.USER_NAME))
                        .withAvatar(resultSet.getString(TableColumn.AVATAR))
                        .build();
                Comment comment = Comment.newCommentBuilder()
                        .withCommentId(resultSet.getLong(TableColumn.COMMENT_ID))
                        .withText(resultSet.getString(TableColumn.TEXT))
                        .withPostDate(resultSet.getTimestamp(TableColumn.COMMENT_POST_DATE))
                        .withUpVotes(resultSet.getInt(TableColumn.UP_VOTE))
                        .withDownVotes(resultSet.getInt(TableColumn.DOWN_VOTE))
                        .withUser(user)
                        .build();
                comments.add(comment);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return comments;
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
