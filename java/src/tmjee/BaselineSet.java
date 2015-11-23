package tmjee;

import contention.abstractions.CompositionalIntSet;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class BaselineSet implements CompositionalIntSet {

    private final BaselineSkipListSet set = new BaselineSkipListSet();


    @Override
    public void fill(int range, long size) {
        try {
            while(set.size() < size) {
                set.addInt(ThreadLocalRandom.current().nextInt(range));
            }
        }catch(Throwable t) {
        }
    }

    @Override
    public boolean addInt(int x) {
        try {
           return set.addInt(x);
        } catch(Throwable t) {}
        return false;
    }

    @Override
    public boolean removeInt(int x) {
        try {
            return set.removeInt(x);
        } catch(Throwable t) {}
        return false;
    }

    @Override
    public boolean containsInt(int x) {
        try {
            return set.containsInt(x);
        } catch(Throwable t) {}
        return false;
    }

    @Override
    public Object getInt(int x) {
        try {
            return set.getInt(x);
        } catch(Throwable t) {}
        return null;
    }

    @Override
    public boolean addAll(Collection<Integer> c) {
        try {
            return set.addAll(c);
        } catch(Throwable t) { }
        return false;
    }

    @Override
    public boolean removeAll(Collection<Integer> c) {
        try {
            return set.removeAll(c);
        } catch(Throwable t) {}
        return false;
    }

    @Override
    public int size() {
        try {
            return set.size();
        }catch(Throwable t) {}
        return 0;
    }

    @Override
    public void clear() {
        try {
           set.clear();
        } catch(Exception e) { }
    }

    @Override
    public Object putIfAbsent(int x, int y) {
        throw new UnsupportedOperationException("not supported");
    }
}
