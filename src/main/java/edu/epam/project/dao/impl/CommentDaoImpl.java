package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.entity.Comment;
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
    public List<Comment> findCommentsByMovieId(long movieId) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_COMMENTS_BY_MOVIE_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userName = resultSet.getString(TableColumn.USER_NAME_FK);
                String text = resultSet.getString(TableColumn.COMMENT);
                String postDate = resultSet.getString(TableColumn.COMMENT_POST_DATE);
                Comment comment = new Comment(text, userName, postDate);
                comments.add(comment);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return comments;
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
        try(Connection connection = ConnectionPool.INSTANCE.getConnection();
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
