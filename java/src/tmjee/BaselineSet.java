package tmjee;

import contention.abstractions.CompositionalIntSet;
import skiplists.sequential.SequentialSkipListIntSet;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class BaselineSet implements CompositionalIntSet {

    //private final BaselineSkipListSet set = new BaselineSkipListSet();
    private final SequentialSkipListIntSet set = new SequentialSkipListIntSet();
    //private final tmjee.impl.MySeqSkipListSet<Integer> set = new tmjee.impl.MySeqSkipListSet<Integer>();


    @Override
    public void fill(int range, long size) {
        try {
            while(set.size() < size) {
                set.addInt(ThreadLocalRandom.current().nextInt(range));
                //set.add(ThreadLocalRandom.current().nextInt(range));
            }
        }catch(Throwable t) {
        }
    }

    @Override
    public boolean addInt(int x) {
        try {
           return set.addInt(x);
            //return set.add(x);
        } catch(Throwable t) {}
        return false;
    }

    @Override
    public boolean removeInt(int x) {
        try {
            return set.removeInt(x);
            //return set.remove(x);
        } catch(Throwable t) {}
        return false;
    }

    @Override
    public boolean containsInt(int x) {
        try {
            return set.containsInt(x);
            //return set.contains(x);
        } catch(Throwable t) {}
        return false;
    }

    @Override
    public Object getInt(int x) {
        try {
            return set.getInt(x);
            //return set.contains(x) ? x : null;
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
