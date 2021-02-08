package edu.epam.project.sevice.impl;

import edu.epam.project.dao.AdminDao;
import edu.epam.project.dao.impl.AdminDaoImpl;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.AdminService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public boolean updateUserStatusByUserName(boolean status, String userName) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = adminDao.updateUserStatusByUserName(userName, status);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }
}