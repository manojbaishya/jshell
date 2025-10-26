package jshell.concurrency;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jshell.text.TextAnalysis;

public class ConcurrentWorkers {
    private final byte[] fileContents;

    public ConcurrentWorkers(InputStream fileContents) throws IOException {
        this.fileContents = fileContents.readAllBytes();
        fileContents.close();
    }

    public void readFileInSequence(int[] lineNumbers) {
        System.out.println("INFO readFileInSequence");
        System.out.println("**************Read file using single OS threads**************");

        try (ExecutorService manager = Executors.newSingleThreadExecutor()) {
            var coffeeMagazine = new TextAnalysis(new ByteArrayInputStream(fileContents));
            for (int lineNum : lineNumbers) { manager.submit(() -> coffeeMagazine.tokenize(lineNum)); }
        }
    }

    public void readFileManyWorkers(int[] lineNumbers) {
        System.out.println("INFO readFileManyWorkers");
        System.out.println("**************Read file using OS threads**************");

        try (ExecutorService manager = Executors.newFixedThreadPool(lineNumbers.length)) {
            var coffeeMagazine = new TextAnalysis(new ByteArrayInputStream(fileContents));
            for (int lineNum : lineNumbers) { manager.submit(() -> coffeeMagazine.tokenize(lineNum)); }
        }
    }

    public void readFileManyWorkersWithVirtualThreads(int[] lineNumbers) {
        System.out.println("INFO readFileManyWorkersWithVirtualThreads");
        System.out.println("**************Read file using Virtual threads**************");

        try (ExecutorService manager = Executors.newVirtualThreadPerTaskExecutor()) {
            var coffeeMagazine = new TextAnalysis(new ByteArrayInputStream(fileContents));
            for (int lineNum : lineNumbers) { manager.submit(() -> coffeeMagazine.tokenize(lineNum)); }
        }
    }
}
