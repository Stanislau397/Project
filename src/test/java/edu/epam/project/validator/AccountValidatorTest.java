package edu.epam.project.validator;

import edu.epam.project.exception.InvalidInputException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AccountValidatorTest {

    AccountValidator accountValidator;

    @BeforeTest
    public void setUp() {
        this.accountValidator = new AccountValidator();
    }

    @Test
    public void testIsValidAccountDataTrue() throws InvalidInputException {
        String userName = "Stanislau";
        String password = "Ljasdj32";
        String email = "Lancer397@gmail.com";
        boolean condition = accountValidator.isValidAccountData(userName, password, email);
        assertTrue(condition);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsValidAccountDataFalse() throws InvalidInputException {
        String userName = "Stanislau__";
        String password = "Ljasdj32ldal;dals;;da";
        String email = "Lancer397@gmailcom";
        boolean condition = accountValidator.isValidAccountData(userName, password, email);
        assertFalse(condition);
    }

    @Test
    public void testIsValidUserNameTrue() throws InvalidInputException {
        String userName = "Stanislau";
        boolean condition = accountValidator.isValidUserName(userName);
        assertTrue(condition);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsValidUserNameFalse() throws InvalidInputException {
        String userName = "1_sdw2ads,d";
        boolean condition = accountValidator.isValidUserName(userName);
        assertFalse(condition);
    }

    @Test
    public void testIsValidEmailTrue() throws InvalidInputException {
        String email = "Lancer397@gmail.com";
        boolean condition = accountValidator.isValidEmail(email);
        assertTrue(condition);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsValidEmailFalse() throws InvalidInputException {
        String email = "Lancer397@gmailcom";
        boolean condition = accountValidator.isValidEmail(email);
        assertFalse(condition);
    }

    @Test
    public void testIsValidPasswordTrue() throws InvalidInputException {
        String password = "Lwerfdc3";
        boolean condition = accountValidator.isValidPassword(password);
        assertTrue(condition);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsValidPasswordFalse() throws InvalidInputException {
        String password = "kjkadsjkjkajkskakjdjkmkdasmkm";
        boolean condition = accountValidator.isValidPassword(password);
        assertFalse(condition);
    }

    @AfterTest
    public void tierDown() {
        this.accountValidator = null;
    }
}