package edu.epam.project;

import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.impl.CommentDaoImpl;
import edu.epam.project.entity.Comment;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.core.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws DaoException {
        CommentDao commentDao = new CommentDaoImpl();
        System.out.println(commentDao.upVoteComment(1, "Stanislau", 1, 1));
    }
}
