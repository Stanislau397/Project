package edu.epam.project;

import org.apache.logging.log4j.core.util.ArrayUtils;

import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{2,4,1,3};
        Arrays.sort(array, Collections.reverseOrder());
        System.out.println(Arrays.toString(array));
    }
}
