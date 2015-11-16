package tmjee;

import contention.abstractions.CompositionalIntSet;
import org.deuce.Atomic;
import skiplists.sequential.SequentialSkipListIntSet;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class NoOpSet implements CompositionalIntSet {


    private Set<Integer> set = Collections.synchronizedSet(SkiplistSet.create(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }));

    @Override
    public void fill(int range, long size) {
            try {
                while(set.size() < size) {
                    set.add(ThreadLocalRandom.current().nextInt(range));
                }
            }catch(Throwable t) {
            }
    }

    @Override
    public boolean addInt(int x) {
        try {
            return set.add(x);
        }catch(Throwable t) {
            return true;
        }
    }

    @Override
    public boolean removeInt(int x) {
        try {
            return set.remove(x);
        }catch(Throwable t) {
            return true;
        }
    }

    @Override
    public boolean containsInt(int x) {
        try {
            return set.contains(x);
        }catch(Throwable t) {
            return true;
        }
    }

    @Override
    public Object getInt(int x) {
        try {
        return set.contains(x) ? x : null;
        }catch(Throwable t) {
            return null;
        }
    }

    @Override
    public boolean addAll(Collection<Integer> c) {
        try {
            return set.addAll(c);
        }catch(Throwable t) {
            return true;
        }
    }

    @Override
    public boolean removeAll(Collection<Integer> c) {
        try {
            return set.removeAll(c);
        }catch(Throwable t) {
            return true;
        }
    }

    @Override
    public int size() {
        try {
            return set.size();
        }catch(Throwable t) {
            return 0;
        }
    }

    @Override
    public void clear() {
        try {
            set.clear();
        }catch(Throwable t) {
            return;
        }
    }

    @Override
    public Object putIfAbsent(int x, int y) {
        throw new UnsupportedOperationException("not supported");
    }
}
