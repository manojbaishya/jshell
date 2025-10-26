package jshell.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    private Regex() {}

    public static void regexDemo1() {
        System.out.println("INFO regexDemo1");

        String text = "This is the text which is to be searched for occurrences of the word 'is'.";
        String searchPattern = "is";
        System.out.printf("Text: %s%n", text);
        System.out.println("Searching for occurrences of: " + searchPattern);
        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.printf("found: %d : %d - %d%n", count, matcher.start(), matcher.end());
        }
    }
}
