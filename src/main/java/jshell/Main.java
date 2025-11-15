package jshell;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jshell.text.Regex;

public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);

    static void main(String[] args) throws IOException {
        logger.atInfo().log("Hello, World!");
        logger.atInfo().log("Running program '{}' on Java VM version {} in path '{}'", Main.class.getCanonicalName(), Runtime.version().feature(), System.getProperty("java.home"));

        logger.atInfo().log("Welcome to the Java Scripting playground!");

        if (args.length > 0) for (String arg : args) logger.atInfo().log("Argument: {}", arg);
        else logger.atInfo().log("No arguments provided.%n%n");

        String userDirectory = System.getProperty("user.dir");
        logger.atInfo().log("Current working directory: {}", userDirectory);

        // Demos
        Demos.dataGenerator(5000);

        Demos.filterLinesDemo();
        Regex.regexDemo1();
        Demos.textAnalyserDemo();
        Demos.tokenizerDemo();
        Demos.concurrentWorkersDemo();
    }
}
