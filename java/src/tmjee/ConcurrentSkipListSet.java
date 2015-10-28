package tmjee;

import contention.abstractions.CompositionalIntSet;
import org.deuce.Atomic;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ConcurrentSkipListSet implements CompositionalIntSet {

    private Set<Integer> set  = new java.util.concurrent.ConcurrentSkipListSet<Integer>();


    @Override
    @Atomic(metainf = "elastic")
    public void fill(int range, long size) {
        int count =0;
        while(set.size() < size && (count/2)<size) {
            set.add(ThreadLocalRandom.current().nextInt(range));
            count++;
        }
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean addInt(int x) {
        return set.add(x);
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean removeInt(int x) {
        return set.remove(x);
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean containsInt(int x) {
        return set.contains(x);
    }

    @Override
    @Atomic(metainf = "elastic")
    public Object getInt(int x) {
        return set.contains(x) ? x : null;
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean addAll(Collection<Integer> c) {
        return set.addAll(c);
    }

    @Override
    @Atomic(metainf = "elastic")
    public boolean removeAll(Collection<Integer> c) {
        return set.removeAll(c);
    }

    @Override
    @Atomic(metainf = "elastic")
    public int size() {
        return set.size();
    }

    @Override
    @Atomic(metainf = "elastic")
    public void clear() {
        set.clear();
    }

    @Override
    @Atomic(metainf = "elastic")
    public Object putIfAbsent(int x, int y) {
        throw new UnsupportedOperationException("");
    }
}
