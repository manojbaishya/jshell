package jshell.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jshell.text.TextAnalysis;

public class ConcurrentWorkers {
    private final String filepath;

    public ConcurrentWorkers(String filepath) { this.filepath = filepath; }

    public void readFileInSequence(int[] lineNumbers) {
        System.out.println("**************Read file using single OS threads**************");

        try (ExecutorService manager = Executors.newSingleThreadExecutor()) {
            var coffeeMagazine = new TextAnalysis(filepath);
            for (int lineNum : lineNumbers) {
                manager.submit(() -> coffeeMagazine.tokenize(lineNum));
            }
        }
    }

    public void readFileManyWorkers(int[] lineNumbers) {
        System.out.println("**************Read file using OS threads**************");

        try (ExecutorService manager = Executors.newFixedThreadPool(lineNumbers.length)) {
            var coffeeMagazine = new TextAnalysis(filepath);
            for (int lineNum : lineNumbers) {
                manager.submit(() -> coffeeMagazine.tokenize(lineNum));
            }
        }
    }

    public void readFileManyWorkersWithVirtualThreads(int[] lineNumbers) {
        System.out.println("**************Read file using Virtual threads**************");

        try (ExecutorService manager = Executors.newVirtualThreadPerTaskExecutor()) {
            var coffeeMagazine = new TextAnalysis(filepath);
            for (int lineNum : lineNumbers) {
                manager.submit(() -> coffeeMagazine.tokenize(lineNum));
            }
        }
    }
}
