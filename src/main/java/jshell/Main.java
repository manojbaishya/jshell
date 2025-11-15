package jshell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);

    static void main(String[] args) throws IOException {
        logger.atInfo().log("Hello, World!");
        logger.atInfo().log("Welcome to the Java Scripting playground!");
        logger.atInfo().log("Running program '{}' on Java VM version {} in path '{}'", Main.class.getCanonicalName(), Runtime.version().feature(), System.getProperty("java.home"));

        String userDirectory = System.getProperty("user.dir");
        logger.atInfo().log("Current working directory: {}", userDirectory);

        if (args.length > 0) for (String arg : args) logger.atInfo().log("Argument: {}", arg);
        else logger.atInfo().log("No arguments provided.%n%n");

        var configurationFile = "jshell.properties";
        var configuration = new Properties();
        try(InputStream configurationInput = ClassLoader.getSystemResourceAsStream(configurationFile)) {
            if (configurationInput == null) {
                logger.atError().log("Could not load configuration file '{}'", configurationFile);
                throw new FileNotFoundException("%s not found in classpath!".formatted(configurationFile));
            }
            configuration.load(configurationInput);
        }

        logger.atInfo().log("Loaded configuration file '{}'", configurationFile);
        logger.atInfo().log("Configuration data from '{}'", configurationFile);
        for (var configEntry : configuration.entrySet()) {
            logger.atInfo().log("Key: {}, Value: {}", configEntry.getKey(), configEntry.getValue());
        }

        //      ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        //      ┃                          Demos                           ┃
        //      ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
        logger.atInfo().log("━━━━━━━━━━━━━━━━━━━━━━━━ Executing demos ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Demos.dataGenerator(5000);
        Demos.filterLinesDemo();
        Demos.regexDemo();
        Demos.textAnalyserDemo();
        Demos.tokenizerDemo();
        Demos.concurrentWorkersDemo();

        Demos.optionalDemo();
    }
}
