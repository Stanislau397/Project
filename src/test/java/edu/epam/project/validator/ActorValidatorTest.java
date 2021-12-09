package edu.epam.project.validator;

import edu.epam.project.exception.InvalidInputException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ActorValidatorTest {

    ActorValidator actorValidator;

    @BeforeTest
    public void setUp() {
        this.actorValidator = new ActorValidator();
    }

    @Test
    public void testIsValidFirstNameTrue() throws InvalidInputException {
        String firstName = "Keanu";
        boolean condition = actorValidator.isValidFirstName(firstName);
        assertTrue(condition);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsValidFirstNameFalse() throws InvalidInputException {
        String firstName = "Keanu__";
        boolean condition = actorValidator.isValidFirstName(firstName);
        assertFalse(condition);
    }

    @Test
    public void testIsValidLastNameTrue() throws InvalidInputException {
        String lastName = "Reeves";
        boolean condition = actorValidator.isValidLastName(lastName);
        assertTrue(condition);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsValidLastNameFalse() throws InvalidInputException {
        String lastName = "Reeves__";
        boolean condition = actorValidator.isValidLastName(lastName);
        assertFalse(condition);
    }

    @Test
    public void testIsValidBirthDateTrue() throws InvalidInputException {
        String birthDate = "1999-11-01";
        boolean condition = actorValidator.isValidBirthDate(birthDate);
        assertTrue(condition);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsValidBirthDateFalse() throws InvalidInputException {
        String birthDate = "1999-13-01";
        boolean condition = actorValidator.isValidBirthDate(birthDate);
        assertFalse(condition);
    }

    @AfterTest
    public void tierDown() {
        this.actorValidator = null;
    }
}