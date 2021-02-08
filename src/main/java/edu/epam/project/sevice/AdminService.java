package edu.epam.project.sevice;

import edu.epam.project.exception.ServiceException;

public interface AdminService {

    boolean updateUserStatusByUserName(boolean status, String userName) throws ServiceException;
}
