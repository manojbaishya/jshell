package jshell;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import jshell.modelling.Customer;
import jshell.modelling.CustomerRepository;
import jshell.text.Regex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jshell.concurrency.ConcurrentWorkers;
import jshell.data.Generator;
import jshell.text.TextAnalysis;

public final class Demos {

    final static Logger logger = LoggerFactory.getLogger(Demos.class);

    static void dataGenerator(int numLines) {
        logger.atInfo().log("Generating data..");
        var generator = new Generator(numLines);
        generator.generateData();
    }

    static void filterLinesDemo() throws IOException {
        logger.atInfo().log("filterLinesDemo");

        var rockbandsFileContents = Main.class.getClassLoader().getResourceAsStream("rockbands.txt");
        if (rockbandsFileContents == null) {
            System.err.println("Resource not found: rockbands.txt");
            return;
        }

        var rockbands = new TextAnalysis(rockbandsFileContents);
        List<String> lines = rockbands.filterLinesRegex("J");
        rockbands.processData(lines);
        rockbandsFileContents.close();
    }

    static void textAnalyserDemo() throws IOException {
        logger.atInfo().log("textAnalyserDemo");

        var rockbandsFileContents = Demos.class.getClassLoader().getResourceAsStream("rockbands.txt");
        if (rockbandsFileContents == null) {
            System.err.println("Resource not found: rockbands.txt");
            return;
        }

        var rockbands = new TextAnalysis(rockbandsFileContents);
        rockbands.analyzeText("in");
        rockbandsFileContents.close();
    }

    static void tokenizerDemo() throws IOException {
        logger.atInfo().log("tokenizerDemo");

        var allAboutCoffeeFileContents = Demos.class.getClassLoader().getResourceAsStream("all_about_coffee.txt");
        if (allAboutCoffeeFileContents == null) {
            System.err.println("Resource not found: all_about_coffee.txt");
            return;
        }

        var coffeeMagazine = new TextAnalysis(allAboutCoffeeFileContents);
        coffeeMagazine.tokenize(559);
        allAboutCoffeeFileContents.close();
    }

    static void concurrentWorkersDemo() throws IOException {
        logger.atInfo().log("concurrentWorkersDemo");

        var lineNumbers = new int[]{667, 14457, 14670, 15571, 3255};
        var concurrentWorkers = new ConcurrentWorkers("all_about_coffee.txt");
        concurrentWorkers.readFileInSequence(lineNumbers);
        concurrentWorkers.readFileManyWorkers(lineNumbers);
        concurrentWorkers.readFileManyWorkersWithVirtualThreads(lineNumbers);
    }

    static void regexDemo() throws IOException {
        Regex.regexDemo1();
    }

    static void optionalDemo() throws IOException {
        logger.atInfo().log("--- Optional<T> Demo ---");

        int numOfCustomers = 500;
        var repo = new CustomerRepository(numOfCustomers);
        logger.atInfo().log("Getting {} customers", numOfCustomers);

        List<Optional<Customer>> customers = repo.getCustomers();
        for (int i = 0, n = customers.size(); i < n; i++) {
            var customer = customers.get(i);

            if (customer.isPresent()) logger.atInfo().log("Customer found at index {}: {}", i, customer.get().toString());
            else logger.atInfo().log("Customer not found at index {}.", i);
        }
    }
}
