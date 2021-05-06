package edu.epam.project;

import edu.epam.project.dao.CommentDao;
import edu.epam.project.dao.impl.CommentDaoImpl;
import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Director;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.RatingService;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.RatingServiceImpl;
import org.apache.logging.log4j.core.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws DaoException, ServiceException {
        MovieService movieService = new MovieServiceImpl();
        Director director;
        int[] arr = new int[]{1,2,1,2,1,2,3};
        boolean bool = false;
        List<Integer> list = new ArrayList<>();
        for (Integer i : arr) {
            list.add(i);
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(i);
                    i = i - 1;
                    break;
                }
            }
        }
        System.out.println(list);
    }
}
