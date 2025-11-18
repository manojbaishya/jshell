package jshell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import jshell.combinatorics.Pairs;
import jshell.data.Point;
import jshell.data.PointRepository;
import jshell.data.PointService;
import jshell.datetimes.DateTime;
import jshell.modelling.Customer;
import jshell.modelling.CustomerRepository;
import jshell.text.Regex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jshell.concurrency.ConcurrentWorkers;
import jshell.data.Generator;
import jshell.text.TextAnalysis;
import org.yaml.snakeyaml.Yaml;

public final class Demos {

    final static Logger logger = LoggerFactory.getLogger(Demos.class);

    static void csvDataGenerator(int numLines) {
        logger.atInfo().log("Generating data..");
        var generator = new Generator(numLines);
        generator.generateCsvData();
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

            if (customer.isPresent())
                logger.atInfo().log("Customer found at index {}: {}", i, customer.get().toString());
            else logger.atInfo().log("Customer not found at index {}.", i);
        }
    }

    static void dateTimeDemos() throws IOException {
        logger.atInfo().log("--- Date-Time Demo ---");

        var now = new DateTime();
        logger.atInfo().log("DateTime is {}", now.toString());
    }

    static void configurationLoaderProperties() throws IOException {
        final String configurationFile = "/jshell.properties";
        logger.atInfo().log("Loading configuration file '{}'.", configurationFile);
        var configuration = new Properties();
        try (InputStream configurationContents = Demos.class.getResourceAsStream(configurationFile)) {
            if (configurationContents == null) {
                logger.atError().log("Could not load configuration file '{}'", configurationFile);
                throw new FileNotFoundException("%s not found in classpath!".formatted(configurationFile));
            }
            configuration.load(configurationContents);
        }

        logger.atInfo().log("Loaded configuration file '{}'", configurationFile);
        logger.atInfo().log("Configuration data from '{}'", configurationFile);
        for (var configEntry : configuration.entrySet()) {
            logger.atInfo().log("Key: {}, Value: {}", configEntry.getKey(), configEntry.getValue());
        }
    }

    static void configurationLoaderYaml() throws IOException {
        final String configurationFile = "/jshell.yaml";
        logger.atInfo().log("Loading YAML configuration file '{}'.", configurationFile);

        var configuration = new Yaml();
        Map<String, Object> configurationData;
        try (InputStream configurationContents = Demos.class.getResourceAsStream(configurationFile)) {
            if (configurationContents == null) {
                logger.atError().log("Could not load YAML configuration file '{}'", configurationFile);
                throw new FileNotFoundException("%s not found in classpath!".formatted(configurationFile));
            }
            configurationData = configuration.load(configurationContents);
        }

        logger.atInfo().log("Loaded YAML configuration file '{}'", configurationFile);
        logger.atInfo().log("Configuration data from YAML '{}'", configurationFile);
        for (var configEntry : configurationData.entrySet()) {
            logger.atInfo().log("YAML Key: {}, Value: {}", configEntry.getKey(), configEntry.getValue());
        }
    }

    static void pointDataDemos(int count, int numCombinations) throws IOException {
        logger.atInfo().log("--- pointDataDemos() ---");

        var pointRepo = new PointRepository(count);
        List<Point> pointData = pointRepo.getPoints();

        var pointService = new PointService();
        Map<Pairs.Pair<Point, Point>, Double> distances =
                Pairs.enumerate(pointData, numCombinations)
                    .collect(Collectors.toMap(
                            pointPair -> pointPair,
                            pointPair -> pointService.euclideanDistance(pointPair.first(), pointPair.second())));

        for (var distanceComputation : distances.entrySet()) {
            logger.atInfo().log(
                    "Distance between {} and {} = {} units",
                    distanceComputation.getKey().first(),
                    distanceComputation.getKey().second(),
                    distanceComputation.getValue()
            );
        }
    }
}
