package javakit.concurrency;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javakit.text.TextAnalysis;

public class ConcurrentWorkers {
    private static final Logger logger = LoggerFactory.getLogger(ConcurrentWorkers.class);
    private final byte[] fileContents;

    public ConcurrentWorkers(String filepath) throws IOException {
        var fileStream = ConcurrentWorkers.class.getClassLoader().getResourceAsStream(filepath);
        if (fileStream == null) {
            this.fileContents = null;
            logger.atError().log("Resource not found: {}", filepath);
            return;
        }
        
        this.fileContents = fileStream.readAllBytes();
        fileStream.close();
    }

    public void readFileInSequence(int[] lineNumbers) {
        logger.atInfo().log("readFileInSequence");
        logger.atInfo().log("**************Read file using single OS threads**************");

        try (ExecutorService manager = Executors.newSingleThreadExecutor()) {
            var coffeeMagazine = new TextAnalysis(new ByteArrayInputStream(fileContents));
            for (int lineNum : lineNumbers) { manager.submit(() -> coffeeMagazine.tokenize(lineNum)); }
        }
    }

    public void readFileManyWorkers(int[] lineNumbers) {
        logger.atInfo().log("readFileManyWorkers");
        logger.atInfo().log("**************Read file using OS threads**************");

        try (ExecutorService manager = Executors.newFixedThreadPool(lineNumbers.length)) {
            var coffeeMagazine = new TextAnalysis(new ByteArrayInputStream(fileContents));
            for (int lineNum : lineNumbers) { manager.submit(() -> coffeeMagazine.tokenize(lineNum)); }
        }
    }

    public void readFileManyWorkersWithVirtualThreads(int[] lineNumbers) {
        logger.atInfo().log("readFileManyWorkersWithVirtualThreads");
        logger.atInfo().log("**************Read file using Virtual threads**************");

        try (ExecutorService manager = Executors.newVirtualThreadPerTaskExecutor()) {
            var coffeeMagazine = new TextAnalysis(new ByteArrayInputStream(fileContents));
            for (int lineNum : lineNumbers) { manager.submit(() -> coffeeMagazine.tokenize(lineNum)); }
        }
    }
}
