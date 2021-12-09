package edu.epam.project.validator;

import edu.epam.project.exception.InvalidInputException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MovieValidatorTest {

    MovieValidator movieValidator;

    @BeforeTest
    public void setUp() {
        this.movieValidator = new MovieValidator();
    }

    @Test
    public void testIsImageValidTrue() throws InvalidInputException {
        String image = "1.jpg";
        boolean condition = movieValidator.isImageValid(image);
        assertTrue(condition);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsImageValidFalse() throws InvalidInputException {
        String image = "1.txt";
        boolean condition = movieValidator.isImageValid(image);
        assertFalse(condition);
    }

    @Test
    public void testIsTitleValidTrue() throws InvalidInputException {
        String title = "The Matrix";
        boolean condition = movieValidator.isTitleValid(title);
        assertTrue(condition);

    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsTitleValidFalse() throws InvalidInputException {
        String title = "The Matrixjkadsjkdjkasjkjkdas" +
                "jkjdsajksadjkjkadskmmkadsmkmkadsmkadskjdkaskjjwjqjqkksadkkadskmmk";
        boolean condition = movieValidator.isTitleValid(title);
        assertFalse(condition);
    }

    @Test
    public void testIsReleaseDateValidTrue() throws InvalidInputException {
        String releaseDate = "1999-12-01";
        boolean condition = movieValidator.isReleaseDateValid(releaseDate);
        assertTrue(condition);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testIsReleaseDateValidFalse() throws InvalidInputException {
        String releaseDate = "1999-13-33";
        boolean condition = movieValidator.isReleaseDateValid(releaseDate);
        assertTrue(condition);
    }

    @Test
    public void testIsRunTimeValidTrue() throws InvalidInputException {
        int runTime = 135;
        boolean condition = movieValidator.isRunTimeValid(runTime);
        assertTrue(condition);
    }

    @Test
    public void testIsDescriptionValidTrue() throws InvalidInputException {
        String description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
        boolean condition = movieValidator.isDescriptionValid(description);
        assertTrue(condition);
    }

    @AfterTest
    public void tierDown() {
        this.movieValidator = null;
    }
}