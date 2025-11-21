package javakit.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Regex {
    private final static Logger logger = LoggerFactory.getLogger(Regex.class);

    private Regex() {}

    public static void regexDemo1() {
        logger.atInfo().log("regexDemo1");

        String searchPattern = "is";
        Pattern pattern = Pattern.compile(searchPattern);

        String text = "This is the text which is to be searched for occurrences of the word 'is'.";
        logger.atInfo().log("Searching for occurrences of: {} in text: {}", searchPattern, text);
        Matcher textmatcher = pattern.matcher(text);
        int count = 0;
        while (textmatcher.find()) {
            count++;
            logger.atInfo().log("found: {} : {} - {}", count, textmatcher.start(), textmatcher.end());
        }
    }
}