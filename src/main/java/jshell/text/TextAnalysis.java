package jshell.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextAnalysis {
    private final static Logger logger = LoggerFactory.getLogger(TextAnalysis.class);
    private final InputStream fileContents;

    public TextAnalysis(InputStream fileContents) { this.fileContents = fileContents; }

    public void analyzeText(String pattern) {
        logger.atInfo().setMessage("Analyzing text for pattern '{}'...").addArgument(pattern).log();

        try (var reader = new BufferedReader(new InputStreamReader(fileContents))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (pattern != null && !pattern.isEmpty()) {
                    if (line.contains(pattern)) { logger.atInfo().setMessage("Line {}: {}").addArgument(lineNumber).addArgument(line).log(); }
                } else {
                    logger.atInfo().setMessage("Line {}: {}").addArgument(lineNumber).addArgument(line).log();
                }
            }
        } catch (IOException error) {
            logger.atError().setMessage("Error reading file: {}").addArgument(error.getMessage()).log();
        }
    }

    public List<String> filterLines(String pattern) {
        List<String> lines = new ArrayList<>();

        try (var reader = new BufferedReader(new InputStreamReader(fileContents))) {
            String line;
            if (pattern.isBlank()) {
                while ((line = reader.readLine()) != null) lines.add(line);
            } else {
                while ((line = reader.readLine()) != null) if (line.matches(pattern)) lines.add(line);
            }
        } catch (IOException error) {
            logger.atError().setMessage("Error reading file: {}").addArgument(error.getMessage()).log();
        }
        return lines;
    }

    public List<String> filterLinesRegex(String pattern) {
        List<String> lines = new ArrayList<>();

        try (var reader = new BufferedReader(new InputStreamReader(fileContents))) {

            String line;
            if (pattern.isBlank()) {
                while ((line = reader.readLine()) != null) lines.add(line);
            } else {
                var regex = Pattern.compile(pattern);
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = regex.matcher(line);
                    if (matcher.find()) { lines.add(line); }
                }
            }
        } catch (IOException error) {
            logger.atError().setMessage("Error reading file: {}").addArgument(error.getMessage()).log();
        }
        return lines;
    }

    public void processData(List<String> lines) { for (String line : lines) logger.atInfo().log(line); }

    public void tokenize(int lineNumber) {
        logger.atInfo().setMessage("Tokenizing line number '{}'...").addArgument(lineNumber).log();

        String line;
        try (var reader = new BufferedReader(new InputStreamReader(fileContents))) {
            for (int i = 1; i < lineNumber; i++) {
                line = reader.readLine();
                if (line == null) return;
            }

            line = reader.readLine();
            if (line == null) return;

            logger.atInfo().setMessage("Line {}: {}").addArgument(lineNumber).addArgument(line).log();
            logger.atInfo().log("Tokenized words are:");
            String[] words = line.split("\\s");
            for (String word : words) { logger.atInfo().setMessage("'{}'").addArgument(word).log(); }
        } catch (IOException error) {
            logger.atError().setMessage("Error reading file: {}").addArgument(error.getMessage()).log();
        }
    }
}
