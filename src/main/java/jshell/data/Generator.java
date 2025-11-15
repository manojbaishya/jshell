package jshell.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import me.tongfei.progressbar.DelegatingProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Generator {
    static final Logger logger = LoggerFactory.getLogger(Generator.class);
    private final int numLines;
    private final Faker faker;

    public Generator(int numLines) {
        this.numLines = numLines;
        this.faker = new Faker();
    }

    public void generateData() {
        String filename = "generated-data.%s-%s.csv".formatted(
                Instant.now().toString().replaceAll("(//|:)", "."),
                UUID.randomUUID());
        logger.atInfo().log("Writing {} lines of data to file: '{}'...", numLines, filename);
        try (
                var writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
                var pb = new ProgressBarBuilder().setStyle(ProgressBarStyle.ASCII).setInitialMax(numLines).setTaskName("Generator").setConsumer(new DelegatingProgressBarConsumer(logger::info, 100)).build()
        ) {
            writer.println("Name,Address,Company,Job,Domain,University,SSN");
            for (int i = 0; i < numLines; i++) {
                var line = "%s,%s,%s,%s,%s,%s,%s".formatted(
                        faker.name().fullName(),
                        faker.address().fullAddress().replace(", ", " "),
                        faker.company().name().replace(", ", " "),
                        faker.job().title(),
                        faker.job().field(),
                        faker.university().name().replace(", ", " "),
                        faker.idNumber().ssnValid());
                writer.println(line);
                Thread.sleep(1);
                pb.step();
            }
        } catch (IOException | InterruptedException e) {
            logger.atError().log("An error occurred: {}", e.getMessage());
        }

        logger.atInfo().log("Wrote {} lines of data to file: '{}' - Done!", numLines, filename);
    }
}
