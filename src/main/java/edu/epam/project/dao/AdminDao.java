package edu.epam.project.dao;

import edu.epam.project.exception.DaoException;

public interface AdminDao {

    boolean updateUserStatusByUserName(String userName, boolean status) throws DaoException;
}
