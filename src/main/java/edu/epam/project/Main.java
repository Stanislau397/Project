package edu.epam.project;

import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.RatingService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.RatingServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws ServiceException {
        int[][] array = new int[][]{{1, 2, 3, 4}, {1, 2, 3}};
        int[] array1 = new int[]{1, 2, 3, 4};
        int number = 7;
        String str = "asdws";
        System.out.println(reverse(str));
        System.out.println(sum(array));
        System.out.println(Arrays.toString(findNumber(array1, number)));
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static int sum(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum = sum + array[i][j];
            }
        }
        return sum;
    }

    public static int[] findNumber(int[] array, int number) {
        int sum;
        int[] twoNumbers = new int[2];
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length + 1; j++) {
                sum = i + j;
                if (sum == number) {
                    twoNumbers[0] = i;
                    twoNumbers[1] = j;
                    break;
                }
            }
        }
        return twoNumbers;
    }
}
