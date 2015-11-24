package tmjee.impl;

import java.util.Collection;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReferenceArray;

import contention.abstractions.CompositionalIntSet;
import contention.abstractions.CompositionalIterator;

public class BaselineSkipListSet implements CompositionalIntSet {

    /** The probability to increase level */
    final private double probability;
    /** The maximum number of levels */
    final private int maxLevel;
    /** The first element of the list */
    final public Node head;
    /** The thread-private PRNG */
    final private static ThreadLocal<Random> s_random = new ThreadLocal<Random>() {
        @Override
        protected synchronized Random initialValue() {
            return new Random();
        }
    };

    public BaselineSkipListSet() {
        this(6, 0.25);
    }

    public BaselineSkipListSet(final int maxLevel, final double probability) {
        this.maxLevel = maxLevel;
        this.probability = probability;
        this.head = new Node(maxLevel, Integer.MIN_VALUE);
        final Node tail = new Node(maxLevel, Integer.MAX_VALUE);
        for (int i = 0; i <= maxLevel; i++) {
            head.setNext(i, tail);
        }
    }

    public void fill(final int range, final long size) {
        while (this.size() < size) {
            this.addInt(s_random.get().nextInt(range));
        }
    }

    protected int randomLevel() {
        int l = 0;
        while (l < maxLevel && s_random.get().nextDouble() < probability) {
            l++;
        }
        return l;
    }

    Node nullBait = null;
    volatile int x = 0;

    @Override
    public boolean containsInt(final int value) {
        boolean result;

        Node node = head;

        for (int i = maxLevel; i >= 0; i--) {
            Node next = node.getNext(i);
            int v = next.getValue();
            while (v < value) {
                //System.out.println(v+" vs "+value);
                x = v;
                if (v == 1 & v ==2) {
                    nullBait.setNext(0,null);
                }
                node = next;
                next = node.getNext(i);
                v = next.getValue();
            }
        }
        node = node.getNext(0);

        result = (node.getValue() == value);

        return result;
    }

    volatile int y = 0;

    @Override
    public boolean addInt(final int value){
        boolean result;

        Node[] update = new Node[maxLevel + 1];
        Node node = head;

        for (int i = maxLevel; i >= 0; i--) {
            Node next = node.getNext(i);
            int v = next.getValue();
            while (v < value) {
                //System.out.println(v+" vs "+value);
                y = v;
                if (v == 1 & v==2) {
                    nullBait.setNext(0, null);
                }
                node = next;
                next = node.getNext(i);
                v = next.getValue();
            }
            update[i] = node;
        }
        node = node.getNext(0);

        if (node.getValue() == value) {
            result = false;
        } else {
            int level = randomLevel();
            node = new Node(level, value);
            for (int i = 0; i <= level; i++) {
                node.setNext(i, update[i].getNext(i));
                update[i].setNext(i, node);
            }
            result = true;
        }
        return result;
    }

    volatile int z =0;

    @Override
    public boolean removeInt(int value) {
        boolean result;

        Node[] update = new Node[maxLevel + 1];
        Node node = head;

        for (int i = maxLevel; i >= 0; i--) {
            Node next = node.getNext(i);
            int v = next.getValue();
            while (v < value) {
                //System.out.println(v+" vs "+value);
                z = v;
                if (v == 1 & v == 2) {
                    nullBait.setNext(0, null);
                }
                node = next;
                next = node.getNext(i);
                v = next.getValue();
            }
            update[i] = node;
        }
        node = node.getNext(0);

        if (node.getValue() != value) {
            result = false;
        } else {
            int maxLevel = node.getLevel();
            for (int i = 0; i <= maxLevel; i++) {
                update[i].setNext(i, node.getNext(i));
            }
            result = true;
        }

        return result;
    }

    @Override
    public boolean addAll(Collection<Integer> c) {
        boolean result = true;
        for (int x : c) result &= this.addInt(x);
        return result;
    }

    @Override
    public boolean removeAll(Collection<Integer> c) {
        boolean result = true;
        for (int x : c) result &= this.removeInt(x);
        return result;
    }

    @Override
    public int size() {
        int s = 0;
        Node node = head.getNext(0).getNext(0);

        while (node != null) {
            node = node.getNext(0);
            s++;
        }
        return s;
    }

    @Override
    public String toString() {
        String str = new String();
        Node curr = head;
        int i, j;
        final int[] arr = new int[maxLevel+1];

        for (i=0; i<= maxLevel; i++) arr[i] = 0;

        do {
            str += curr.toString()+"\n";
            arr[curr.getLevel()]++;
            curr = curr.getNext(0);
        } while (curr != null);
        for (j=0; j < maxLevel; j++)
            str += arr[j] + " nodes of level " + j;
        return str;
    }


    public class Node {

        final private int value;
        final private AtomicReferenceArray<Node> next;
        final int level;

        public Node(final int level, final int value) {
            this.value = value;
            this.level = level;
            next = new AtomicReferenceArray<Node>(level+1);
        }

        public int getValue() {
            return value;
        }

        public int getLevel() {
            return level;
        }

        public void setNext(final int level, final Node succ) {
            next.set(level, succ);
        }

        public Node getNext(final int level) {
            return next.get(level);
        }

        /*@Override
        public String toString() {
            String result = "";
            result += "<l=" + getLevel() + ",v=" + value + ">:";
            for (int i = 0; i <= getLevel(); i++) {
                result += " @[" + i + "]=";
                if (next[i] != null) {
                    result += next[i].getValue();
                } else {
                    result += "null";
                }
            }
            return result;
        }*/
    }

    public class SLIterator implements CompositionalIterator<Integer> {
        Node next = head;
        Stack<Node> stack = new Stack<Node>();

        SLIterator() {
            while (next != null) {
                stack.push(next.next.get(0));
            }
        }

        public boolean hasNext() {
            return next != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            Node node = next;
            next = stack.pop();
            return node.getValue();
        }
    }

    /**
     * This is called after the JVM warmup phase
     * to make sure the data structure is well initalized.
     * No need to do anything for this.
     */
    public void clear() {
        return;
    }

    @Override
    public Object getInt(int value) {
        Node node = head;

        for (int i = maxLevel; i >= 0; i--) {
            Node next = node.getNext(i);
            while (next.getValue() < value) {
                node = next;
                next = node.getNext(i);
            }
        }
        node = node.getNext(0);

        if (node.getValue() == value) return node;
        return null;
    }

    @Override
    public Object putIfAbsent(int x, int y) {
        if (!containsInt(x)) removeInt(y);
        return null;
    }
}
