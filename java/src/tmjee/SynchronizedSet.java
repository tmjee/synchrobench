package tmjee;

import contention.abstractions.CompositionalIntSet;
import skiplists.sequential.SequentialSkipListIntSet;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SynchronizedSet implements CompositionalIntSet {

    private final SequentialSkipListIntSet set = new SequentialSkipListIntSet();


    @Override
    public synchronized void fill(int range, long size) {
        while(set.size() < size) {
            set.addInt(ThreadLocalRandom.current().nextInt(range));
        }
    }

    @Override
    public synchronized boolean addInt(int x) {
        return set.addInt(x);
    }

    @Override
    public synchronized boolean removeInt(int x) {
        return set.removeInt(x);
    }

    @Override
    public synchronized boolean containsInt(int x) {
        return set.containsInt(x);
    }

    @Override
    public synchronized Object getInt(int x) {
        return set.containsInt(x) ? x : null;
    }

    @Override
    public synchronized boolean addAll(Collection<Integer> c) {
        return set.addAll(c);
    }

    @Override
    public synchronized boolean removeAll(Collection<Integer> c) {
        return set.removeAll(c);
    }

    @Override
    public synchronized int size() {
        return set.size();
    }

    @Override
    public synchronized void clear() {
        set.clear();
    }

    @Override
    public synchronized String toString() {
        return set.toString();
    }

    @Override
    public synchronized Object putIfAbsent(int x, int y) {
        throw new UnsupportedOperationException("not supported");
    }
}
