package javakit;

import javakit.combinatorics.Pairs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);

    static void main(String[] args) throws IOException {
        logger.atInfo().log("Hello, World!");
        logger.atInfo().log("Welcome to the Java playground!");
        logger.atInfo().log("Running program '{}' on Java VM version {} in path '{}'", Main.class.getCanonicalName(), Runtime.version().feature(), System.getProperty("java.home"));

        String userDirectory = System.getProperty("user.dir");
        logger.atInfo().log("Current working directory: {}", userDirectory);

        if (args.length > 0) for (String arg : args) logger.atInfo().log("Argument: {}", arg);
        else logger.atInfo().log("No arguments provided.%n%n");

        //      ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        //      ┃                          Demos                           ┃
        //      ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
        logger.atInfo().log("━━━━━━━━━━━━━━━━━━━━━━━━ Executing demos ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Demos.dateTimeDemos();
        Demos.configurationLoaderProperties();
        Demos.configurationLoaderYaml();
        int countPoints = 1000;
        Demos.pointDataDemos(countPoints, Pairs.count(countPoints) / 2);
        Demos.csvDataGenerator(5000);
        Demos.filterLinesDemo();
        Demos.regexDemo();
        Demos.textAnalyserDemo();
        Demos.tokenizerDemo();
        Demos.concurrentWorkersDemo();
        Demos.optionalDemo();
    }
}
