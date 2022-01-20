package edu.epam.project;

import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws ServiceException {
        UserService userService = new UserServiceImpl();
        Optional<User> userOptional = userService.findUserByUserName("Stanislau");
        List<User> users = userService.findAll();
        System.out.println(users.get(0).getUserName());
    }
}
