package jshell.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Regex {
    private final static Logger logger = LoggerFactory.getLogger(TextAnalysis.class);

    private Regex() {}

    public static void regexDemo1() {
        logger.atInfo().log("regexDemo1");

        String text = "This is the text which is to be searched for occurrences of the word 'is'.";
        String searchPattern = "is";
        logger.atInfo().setMessage("Text: {}").addArgument(text).log();
        logger.atInfo().log("Searching for occurrences of: " + searchPattern);
        Pattern pattern = Pattern.compile(searchPattern);
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
            logger.atInfo().setMessage("found: {} : {} - {}")
            .addArgument(count)
            .addArgument(matcher.start())
            .addArgument(matcher.end())
            .log();
        }
    }
}