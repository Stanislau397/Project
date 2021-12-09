package edu.epam.project.service;

import edu.epam.project.service.impl.UserServiceImpl;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeTest
    public void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    public void testRegister() {

    }

    @Test
    public void testFindByEmailAndPassword() {
    }

    @Test
    public void testFindUserByUserName() {
    }

    @Test
    public void testChangePassword() {
    }

    @Test
    public void testChangeUserName() {
    }

    @Test
    public void testUpdateUserStatusByUserName() {
    }

    @Test
    public void testChangeUserRoleByUserName() {
    }

    @Test
    public void testFindAll() {
    }

    @AfterTest
    public void tierDown() {
        userService = null;
    }
}