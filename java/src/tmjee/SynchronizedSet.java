package tmjee;

import contention.abstractions.CompositionalIntSet;
import skiplists.sequential.SequentialSkipListIntSet;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedSet implements CompositionalIntSet {

    private final SequentialSkipListIntSet set = new SequentialSkipListIntSet();
    Lock lock = new ReentrantLock();


    @Override
    public void fill(int range, long size) {
        lock.lock();
        try {
            while (set.size() < size) {
                set.addInt(ThreadLocalRandom.current().nextInt(range));
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean addInt(int x) {
        lock.lock();
        try {
            return set.addInt(x);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean removeInt(int x) {
        lock.lock();
        try {
            return set.removeInt(x);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsInt(int x) {
        lock.lock();
        try {
            return set.containsInt(x);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public Object getInt(int x) {
        lock.lock();
        try {
            return set.containsInt(x) ? x : null;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean addAll(Collection<Integer> c) {
        lock.lock();
        try {
            return set.addAll(c);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean removeAll(Collection<Integer> c) {
        lock.lock();
        try {
            return set.removeAll(c);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return set.size();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            set.clear();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        lock.lock();
        try {
            return set.toString();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public Object putIfAbsent(int x, int y) {
        lock.lock();
        try {
            throw new UnsupportedOperationException("not supported");
        }finally {
            lock.unlock();
        }
    }
}
