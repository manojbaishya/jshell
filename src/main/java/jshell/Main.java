package jshell;

import java.util.List;

import jshell.concurrency.ConcurrentWorkers;
import jshell.text.Regex;
import jshell.text.TextAnalysis;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println("Welcome to the Java Scripting playground!");

        if (args.length > 0) {
            for (String arg : args) { System.out.println("Argument: " + arg); }
        } else {
            System.out.printf("No arguments provided.%n%n");
        }

        String userDirectory = System.getProperty("user.dir");
        System.out.println("Current working directory: " + userDirectory);

        filterLinesDemo();

        Regex.regexDemo1();
        textAnalyserDemo();
        tokenizerDemo();
        concurrentWorkersDemo();
    }

    static void filterLinesDemo() {
        var rockbands = new TextAnalysis("rockbands.txt");
        List<String> lines = rockbands.filterLinesRegex("J");
        rockbands.processData(lines);
    }

    static void textAnalyserDemo() {
        var rockbands = new TextAnalysis("rockbands.txt");
        rockbands.analyzeText("in");
    }

    static void tokenizerDemo() {
        var coffeeMagazine = new TextAnalysis("all_about_coffee.txt");
        coffeeMagazine.tokenize(559);
    }

    static void concurrentWorkersDemo() {
        int[] lineNumbers = new int[] { 667, 14457, 14670, 15571, 3255 };
        var concurrentWorkers = new ConcurrentWorkers("all_about_coffee.txt");
        concurrentWorkers.readFileInSequence(lineNumbers);
        concurrentWorkers.readFileManyWorkers(lineNumbers);
        concurrentWorkers.readFileManyWorkersWithVirtualThreads(lineNumbers);
    }
}
