package mini.java.basic.interfaces.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa IntegerRandomizer generuje nowe, losowe liczby całkowite w podanym przedziale
 */
public class IntegerRandomizer {
    private Random random;
    long seed = 0;

    public IntegerRandomizer() {
        random = new Random();
    }

    public IntegerRandomizer(long seed) {
        this.seed = seed;
        random = new Random(seed);
    }

    public Integer next(int limit) {
        return random.nextInt(limit);
    }

    public List<Integer> nextList(int length, int limit) {
        List<Integer> al = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            al.add(next(limit));
        }
        return al;
    }

    public Integer[] nextArray(int length, int limit) {
        return nextList(length, limit).toArray(new Integer[length]);
    }

    public void reseed() {
        random = new Random();
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        random = new Random(seed);
        this.seed = seed;
    }

    public Integer getEmpty() {
        return 0;
    }

    public Class getElementClass() {
        return Integer.class;
    }
}
