package edu.epam.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 10};
        System.out.println(Arrays.toString(result(numbers)));
    }

    private static int[] result(int[] numbers) {
        List<Integer> list = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < numbers.length; i++) {
            list.add(i);
            while (list.get(counter) < numbers[i + 1]) {
                int number = list.get(counter) + 1;
                list.add(number);
                counter = counter + 1;
            }
        }
        int[] result1 = new int[list.size()];
        int count = 0;
        for (Integer i : list) {
            result1[count] = list.get(count);
            count = count + 1;
        }
        return result1;
    }
}
