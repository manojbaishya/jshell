package jshell.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

public class Generator {
    static final Logger logger = LoggerFactory.getLogger(Generator.class);
    private int numLines;
    private Faker faker;

    public Generator(int numLines) {
        this.numLines = numLines;
        this.faker = new Faker();
    }

    public void generateData() {
        String filename = "generated-data.%s-%s.csv".formatted(
                Instant.now().toString().replaceAll("(//|:)", "."),
                UUID.randomUUID());
        logger.atInfo().log("Writing data to file: '{}'...", filename);
        try (var writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            writer.println("Name,Address,Company,Job,Domain,University,SSN");
            for (int i = 0; i < numLines; i++) {
                logger.atInfo().log("Writing Line #{}", i + 1);
                writer.println(
                        "%s,%s,%s,%s,%s,%s,%s"
                        .formatted(
                                faker.name().fullName(),
                                faker.address().fullAddress().replace(", ", " "),
                                faker.company().name().replace(", ", " "),
                                faker.job().title(),
                                faker.job().field(),
                                faker.university().name().replace(", ", " "),
                                faker.idNumber().ssnValid()));
            }
        } catch (IOException e) {
            logger.atError().log("An error occurred: " + e.getMessage());
        }

        logger.atInfo().log("Wrote data to file: '{}' - Done!", filename);
    }
}
