package aoc.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import aoc.util.AocUtil;

public class Day04 {

    private static final String DAY_INPUT_FILE = "./day04/input.txt";

    private static List<String> sonar;

    private static void init() throws Exception {
        sonar = AocUtil.readFileToStrings(DAY_INPUT_FILE);
    }

    public static long solvePart1() throws Exception {
        init();
        long sum = 0;
        for (int i = 0; i < sonar.size(); i++) {
            String stringWithoutCardGame = sonar.get(i).split(":")[1];
            String[] winningNumbers = stringWithoutCardGame.split("\\|")[0].split("\\s+");
            String[] myNumbers = stringWithoutCardGame.split("\\|")[1].split("\\s+");
            int numberOfWinningNumbers = -1;
            for (String number : myNumbers) {
                if (List.of(winningNumbers).contains(number)) {
                    numberOfWinningNumbers++;
                }
            }
            if (numberOfWinningNumbers != 0) {
                sum += 1 * Math.pow(2, numberOfWinningNumbers - 1);
            }
        }
        return sum;
    }

    public static long solvePart2() throws Exception {
        init();
        long sum = 0;
        List<Integer> cards = new ArrayList<>();
        for (int i = 0; i < sonar.size(); i++) {
            String stringWithoutCardGame = sonar.get(i).split(":")[1];
            String[] winningNumbers = stringWithoutCardGame.split("\\|")[0].split("\\s+");
            String[] myNumbers = stringWithoutCardGame.split("\\|")[1].split("\\s+");
            int numberOfWinningNumbers = -1;
            for (String number : myNumbers) {
                if (List.of(winningNumbers).contains(number)) {
                    numberOfWinningNumbers++;
                }
            }
            if (numberOfWinningNumbers != 0) {
                cards.add(i+1);
            }
        }
        System.out.println(cards);
        return sum;
    }
}