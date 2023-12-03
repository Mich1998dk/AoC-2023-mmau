package aoc.days;

import aoc.util.AocUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day03 {

    private static final String DAY_INPUT_FILE = "./day03/input.txt";

    private static List<String> sonar;

    private static void init() throws Exception {
        sonar = AocUtil.readFileToStrings(DAY_INPUT_FILE);
    }

    public static long solvePart1() throws Exception {
        init();
        List<Long> sum = new ArrayList<>();
        for (int i = 0; i < sonar.size(); i++) {
            String line = sonar.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if(c != '.' && !isNumber(c)) {
                    findPartNumber(i, j, sum);
                }
            }
        }
        return sum.stream().collect(Collectors.summarizingLong(l -> l)).getSum();
    }

    private static void findPartNumber(int i, int j, List<Long> sum) {
        if (i > 0){
            String l = sonar.get(i -1);
            searchTopOrBottom(l, j, sum);
        }
        if (i < sonar.size() -1) {
            String l = sonar.get(i + 1);
            searchTopOrBottom(l, j, sum);
        }
        searchLeftAndRight(sonar.get(i), j, sum);
    }

    private static void searchTopOrBottom(String l, int j, List<Long> sum) {
        char c = l.charAt(j);
        if(isNumber(c)) {
            int k = j - 1;
            while (k >= 0) {
                char d = l.charAt(k);
                if(isNumber(d)) {
                    k--;
                } else {
                    k++;
                    break;
                }
            }
            if (k < 0) {
                k = 0;
            }
            int m = j + 1;
            while(m < l.length()) {
                char d = l.charAt(m);
                if(isNumber(d)) {
                    m++;
                } else {
                    break;
                }
            }
            sum.add(Long.parseLong(l.substring(k, m)));
        } else {
            searchLeftAndRight(l, j, sum);
        }
    }

    private static void searchLeftAndRight(String l, int j, List<Long> sum) {
        int k = j - 1;
        while (k >= 0) {
            char d = l.charAt(k);
            if (isNumber(d)) {
                k--;
            } else {
                k++;
                break;
            }
        }
        if (k<0) {
            k = 0;
        }
        if (k < j) {
            sum.add(Long.parseLong(l.substring(k,j)));
        }

        int m = j + 1;
        while(m< l.length()) {
            char d = l.charAt(m);
            if (isNumber(d)) {
                m ++;
            } else {
                break;
            }
        }
        if (m > j + 1) {
            sum.add(Long.parseLong(l.substring(j + 1, m)));
        }
    }

    private static boolean isNumber(char s) {
        try {
            Integer.parseInt(String.valueOf(s));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static long solvePart2() throws Exception {
        init();
        long sum = 0;
        for (int i = 0; i < sonar.size(); i++) {
            String line = sonar.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if(c == '*') {
                    List<Long> partNumbers = new ArrayList<>();
                    findPartNumber(i, j, partNumbers);
                    if(partNumbers.size() == 2) {
                        long gearRatio = partNumbers.get(0) * partNumbers.get(1);
                        sum += gearRatio;
                    }
                }
            }
        }
        return sum;
    }
}