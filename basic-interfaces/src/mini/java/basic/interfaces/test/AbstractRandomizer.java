package mini.java.basic.interfaces.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractRandomizer<T> implements TypeAware<T> {
    protected Random random;
    long seed = 0;

    public abstract T next(int limit);

    public List<T> nextList(int length, int limit) {
        List<T> al = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            al.add(next(limit));
        }
        return al;
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


    public T[] nextArray(int length, int limit) {
        return nextList(length,limit).toArray((T[]) Array.newInstance(getElementClass(), length));
    }

}
