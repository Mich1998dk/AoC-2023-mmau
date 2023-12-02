package aoc.days;

import aoc.util.AocUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 {

    private static final String DAY_INPUT_FILE = "./day02/input.txt";

    private static List<String> sonar;

    private static final int maxRed = 12;
    private static final int maxGreen = 13;
    private static final int maxBlue = 14;


    private static void init() throws Exception {
        sonar = AocUtil.readFileToStrings(DAY_INPUT_FILE);
    }

    public static int solvePart1() throws Exception {
        init();
        int sum = 0;
        for (int i = 0; i < sonar.size(); i++) {
            int red = 0;
            int blue = 0;
            int green = 0;
            String[] removeGameNumber = sonar.get(i).split(":");
            String subsets = removeGameNumber[1];
            String[] subsetValues = subsets.split(";");
            for (String subsetGame : subsetValues) {
                String[] cubes = subsetGame.split(",");
                for (String cube : cubes) {
                    String[] cubeValues = cube.strip().split(" ");
                    switch (cubeValues[1]) {
                    case "red":
                        red = Math.max(red, Integer.parseInt(cubeValues[0]));
                        break;
                    case "blue":
                        blue = Math.max(blue, Integer.parseInt(cubeValues[0]));
                        break;
                    case "green":
                        green = Math.max(green, Integer.parseInt(cubeValues[0]));
                        break;
                    }
                }
            }
            if(red <= maxRed && green <= maxGreen && blue <= maxBlue){
                sum += i+1;
            }
        }
        return sum;
    }

    public static int solvePart2() throws Exception {
        init();
        int sum = 0;
        for (int i = 0; i < sonar.size(); i++) {
            int red = 0;
            int blue = 0;
            int green = 0;
            String[] removeGameNumber = sonar.get(i).split(":");
            String subsets = removeGameNumber[1];
            String[] subsetValues = subsets.split(";");
            for (String subsetGame : subsetValues) {
                String[] cubes = subsetGame.split(",");
                for (String cube : cubes) {
                    String[] cubeValues = cube.strip().split(" ");
                    switch (cubeValues[1]) {
                    case "red":
                        red = Math.max(red, Integer.parseInt(cubeValues[0]));
                        break;
                    case "blue":
                        blue = Math.max(blue, Integer.parseInt(cubeValues[0]));
                        break;
                    case "green":
                        green = Math.max(green, Integer.parseInt(cubeValues[0]));
                        break;
                    }
                }
            }
                sum += red * green * blue;
        }
        return sum;
    }
}