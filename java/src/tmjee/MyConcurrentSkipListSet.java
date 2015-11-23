package tmjee;

import contention.abstractions.CompositionalIntSet;
import tmjee.impl.MyConcSkipListSet;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tmjee on 23/11/15.
 */
public class MyConcurrentSkipListSet implements CompositionalIntSet {

    private final MyConcSkipListSet<Integer> set = new MyConcSkipListSet<Integer>();


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
        throw new UnsupportedOperationException("unsupported");
    }
}
