package tmjee;

import contention.abstractions.CompositionalIntSet;
import org.deuce.Atomic;

import java.util.Collection;

public class NoOpSet implements CompositionalIntSet {


    @Override
    @Atomic(metainf = "elastic")
    public void fill(int range, long size) {
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean addInt(int x) {
        return true;
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean removeInt(int x) {
        return true;
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean containsInt(int x) {
        return true;
    }

    @Override
    @Atomic(metainf = "elastic")
    public Object getInt(int x) {
        return null;
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean addAll(Collection<Integer> c) {
        return true;
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean removeAll(Collection<Integer> c) {
        return true;
    }

    @Override
    @Atomic(metainf = "elastic")
    public int size() {
        return 0;
    }

    @Override
    @Atomic(metainf = "elastic")
    public void clear() {

    }

    @Override
    @Atomic(metainf = "elastic")
    public Object putIfAbsent(int x, int y) {
        return null;
    }
}
