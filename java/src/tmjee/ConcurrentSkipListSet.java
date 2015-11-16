package tmjee;

import contention.abstractions.CompositionalIntSet;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ConcurrentSkipListSet implements CompositionalIntSet {

    private Set<Integer> set  = new java.util.concurrent.ConcurrentSkipListSet<Integer>();


    @Override
    public void fill(int range, long size) {
        while(set.size() < size) {
            set.add(ThreadLocalRandom.current().nextInt(range));
        }
    }

    @Override
    public boolean addInt(int x) {
        return set.add(x);
    }

    @Override
    public boolean removeInt(int x) {
        return set.remove(x);
    }

    @Override
    public boolean containsInt(int x) {
        return set.contains(x);
    }

    @Override
    public Object getInt(int x) {
        return set.contains(x) ? x : null;
    }

    @Override
    public boolean addAll(Collection<Integer> c) {
        return set.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<Integer> c) {
        return set.removeAll(c);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public Object putIfAbsent(int x, int y) {
        throw new UnsupportedOperationException("");
    }
}
