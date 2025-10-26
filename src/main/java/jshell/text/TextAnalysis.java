package jshell.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalysis {
    private final String filepath;

    public TextAnalysis(String filepath) { this.filepath = filepath; }

    public void analyzeText(String pattern) {
        System.out.printf("Analyzing text for pattern '%s'...%n", pattern);

        var file = new File(filepath);
        if (!file.exists()) {
            System.err.printf("File does not exist: %s%n", file.getAbsolutePath());
            return;
        }

        try(var reader=new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (pattern != null && !pattern.isEmpty()) {
                    if (line.contains(pattern)) {
                        System.out.printf("Line %d: %s%n", lineNumber, line);
                    }
                } else {
                    System.out.printf("Line %d: %s%n", lineNumber, line);
                }
            }
        } catch (IOException error) {
            System.err.printf("Error reading file: %s%n", error.getMessage());
        }
    }

    public List<String> filterLines(String pattern) {
        List<String> lines = new ArrayList<>();

        var file = new File(filepath);
        if (!file.exists()) throw new IllegalArgumentException("File '%s' not found!".formatted(filepath));

        try(var reader=new BufferedReader(new FileReader(file))) {
            String line;
            if (pattern.isBlank()) {
                while ((line = reader.readLine()) != null)
                    lines.add(line);
            } else {
                while ((line = reader.readLine()) != null)
                    if (line.matches(pattern)) lines.add(line);
            }
        } catch (IOException error) {
            System.err.printf("Error reading file '%s'%n", error.getMessage());
        }
        return lines;
    }

    public List<String> filterLinesRegex(String pattern) {
        List<String> lines = new ArrayList<>();

        var file = new File(filepath);
        if (!file.exists()) throw new IllegalArgumentException("File '%s' not found!".formatted(filepath));

        try(var reader=new BufferedReader(new FileReader(file))) {

            String line;
            if (pattern.isBlank()) {
                while ((line = reader.readLine()) != null)
                    lines.add(line);
            } else {
                var regex = Pattern.compile(pattern);
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = regex.matcher(line);
                    if (matcher.find()) {
                        lines.add(line);
                    }
                }
            }
        } catch (IOException error) {
            System.err.printf("Error reading file '%s'%n", error.getMessage());
        }
        return lines;
    }

    public void processData(List<String> lines) {
        for (String line : lines)
            System.out.println(line);
    }

    public void tokenize(int lineNumber) {
        System.out.printf("Tokenizing line number '%d'...%n", lineNumber);

        var file = new File(filepath);
        if (!file.exists()) {
            System.err.printf("File does not exist: %s%n", file.getAbsolutePath());
            return;
        }

        String line;
        try(var reader=new BufferedReader(new FileReader(file))) {
            for (int i = 1; i < lineNumber; i++) {
                line = reader.readLine();
                if (line == null) return;
            }

            line = reader.readLine();
            if (line == null) return;

            System.out.printf("Line %d: %s%n", lineNumber, line);
            System.out.println("Tokenized words are:");
            String[] words = line.split("\\s");
            for (String word : words) {
                System.out.printf("'%s'%n", word);
            }
        } catch (IOException error) {
            System.err.printf("Error reading file: %s%n", error.getMessage());
        }
    }
}
