package edu.epam.project.service;

import edu.epam.project.exception.ServiceException;

public interface AdminService {

    boolean updateUserStatusByUserName(boolean status, String userName) throws ServiceException;
}
