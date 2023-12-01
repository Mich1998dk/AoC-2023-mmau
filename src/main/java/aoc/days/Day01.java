package aoc.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.util.AocUtil;

public class Day01 {

    private static final String DAY_INPUT_FILE = "./day01/input.txt";

    private static List<String> sonar;

    private static final List<String> numbers = Arrays.asList("zero", "one",
            "two", "three", "four", "five", "six", "seven",
            "eight", "nine");

    private static void init() throws Exception {
        sonar = AocUtil.readFileToStrings(DAY_INPUT_FILE);
    }

    public static String solvePart1() throws Exception {
        init();
        int sum = 0;

        for (int i = 0; i < sonar.size(); i++) {
            Pattern integerPattern = Pattern.compile("-?\\d");
            Matcher matcher = integerPattern.matcher(sonar.get(i));

            List<String> integerList = new ArrayList<>();
            while (matcher.find()) {
                integerList.add(matcher.group());
            }
            if (!integerList.isEmpty()) {
                String concatNumber = integerList.get(0) + integerList.get(integerList.size() - 1);
                sum += tryParseInt(concatNumber);
            }
        }
        return Integer.toString(sum);
    }

    private static int tryParseInt(String integerString) {
        try {
            return Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            for (int j = 0; j < numbers.size(); j++) {
                if (Objects.equals(numbers.get(j), integerString)) {
                    return j;
                }
            }
        }
        return 0;
    }

    public static String solvePart2() throws Exception {
        init();
        int sum = 0;

        for (int i = 0; i < sonar.size(); i++) {
            Pattern integerPattern = Pattern.compile("(?=(\\d|one|two|three|four|five|six|seven|eight|nine))", Pattern.MULTILINE);
            Matcher matcher = integerPattern.matcher(sonar.get(i));

            List<String> integerList = new ArrayList<>();
            while (matcher.find()) {
                for (int j = 1; j <= matcher.groupCount(); j++) {
                    integerList.add(matcher.group(j));
                }
            }
            if (!integerList.isEmpty()) {
                int number1 = tryParseInt(integerList.get(0));
                int number2 = tryParseInt(integerList.get(integerList.size() - 1));
                System.out.println(i  + " " + Integer.parseInt("" + number1 + number2) + " " + integerList.get(0) + integerList.get(integerList.size() - 1) + " " + sonar.get(i));
                sum += Integer.parseInt("" + number1 + number2);
            }
        }
        return Integer.toString(sum);
    }
}