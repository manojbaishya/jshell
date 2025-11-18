package jshell.combinatorics;

import org.jspecify.annotations.NonNull;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Pairs {

    public record Pair<A, B>(A first, B second) {
        @NonNull
        public String toString() {
            return "(%s, %s)".formatted(first.toString(), second.toString());
        }
    }

    public static <T> Stream<Pair<T, T>> enumerate(List<T> list, int m) throws IllegalArgumentException {
        if (m < 0) {
            throw new IllegalArgumentException("m must be non-negative");
        }

        // maximum number of pairs is NC2
        int maxPairs = count(list.size());
        if (m > maxPairs) {
            throw new IllegalArgumentException("m cannot be greater than total number of pairs: %d".formatted(maxPairs));
        }

        return enumerate(list).limit(m);
    }

    public static <T> Stream<Pair<T, T>> enumerate(List<T> list) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new PairIterator<>(list), Spliterator.ORDERED), false);
    }

    public static int count(int n) {
        if (n < 2) return 0;
        return n * (n - 1) / 2;
    }

    public static class PairIterator<T> implements Iterator<Pair<T, T>> {
        private final List<T> list;
        private int i = 0;
        private int j = 1;

        public PairIterator(List<T> list) { this.list = list; }

        @Override
        public boolean hasNext() { return i < list.size() - 1; }

        @Override
        public Pair<T, T> next() {
            if (!hasNext()) throw new NoSuchElementException();

            var pair = new Pair<T, T>(list.get(i), list.get(j));

            j++;

            if (j >= list.size()) {
                i++;
                j = i + 1;
            }

            return pair;
        }
    }
}
