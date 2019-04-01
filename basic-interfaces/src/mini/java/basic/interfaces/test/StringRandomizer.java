package mini.java.basic.interfaces.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa StringRandomizer generuje nowe, losowe napisy całkowite o konkretnej długości
 */
public class StringRandomizer {
    private Random random;
    long seed = 0;

    public StringRandomizer() {
        random = new Random();
    }

    public StringRandomizer(long seed) {
        this.seed = seed;
        random = new Random(seed);
    }

    public String next(int limit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    public List<String> nextList(int length, int limit) {
        List<String> al = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            al.add(next(limit));
        }
        return al;
    }

    public String[] nextArray(int length, int limit) {
        return nextList(length,limit).toArray(new String[length]);
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

    public String getEmpty() {
        return new String();
    }

    public Class getElementClass() {
        return String.class;
    }
}
