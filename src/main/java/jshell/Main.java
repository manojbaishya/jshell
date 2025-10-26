package jshell;

import java.io.IOException;
import java.util.List;

import jshell.concurrency.ConcurrentWorkers;
import jshell.text.Regex;
import jshell.text.TextAnalysis;

public class Main {
    public static void main(String[] args) throws IOException {
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
        System.out.println("INFO filterLinesDemo");

        var rockbandsFileContents = Main.class.getClassLoader().getResourceAsStream("rockbands.txt");
        if (rockbandsFileContents == null) {
            System.err.println("Resource not found: rockbands.txt");
            return;
        }

        var rockbands = new TextAnalysis(rockbandsFileContents);
        List<String> lines = rockbands.filterLinesRegex("J");
        rockbands.processData(lines);
    }

    static void textAnalyserDemo() {
        System.out.println("INFO textAnalyserDemo");

        var rockbandsFileContents = Main.class.getClassLoader().getResourceAsStream("rockbands.txt");
        if (rockbandsFileContents == null) {
            System.err.println("Resource not found: rockbands.txt");
            return;
        }

        var rockbands = new TextAnalysis(rockbandsFileContents);
        rockbands.analyzeText("in");
    }

    static void tokenizerDemo() {
        System.out.println("INFO tokenizerDemo");

        var allAboutCoffeeFileContents = Main.class.getClassLoader().getResourceAsStream("all_about_coffee.txt");
        if (allAboutCoffeeFileContents == null) {
            System.err.println("Resource not found: all_about_coffee.txt");
            return;
        }

        var coffeeMagazine = new TextAnalysis(allAboutCoffeeFileContents);
        coffeeMagazine.tokenize(559);
    }

    static void concurrentWorkersDemo() throws IOException {
        System.out.println("INFO concurrentWorkersDemo");

        var allAboutCoffeeFileContents = Main.class.getClassLoader().getResourceAsStream("all_about_coffee.txt");
        if (allAboutCoffeeFileContents == null) {
            System.err.println("Resource not found: all_about_coffee.txt");
            return;
        }

        int[] lineNumbers = new int[] { 667, 14457, 14670, 15571, 3255 };
        var concurrentWorkers = new ConcurrentWorkers(allAboutCoffeeFileContents);
        concurrentWorkers.readFileInSequence(lineNumbers);
        concurrentWorkers.readFileManyWorkers(lineNumbers);
        concurrentWorkers.readFileManyWorkersWithVirtualThreads(lineNumbers);
    }
}
