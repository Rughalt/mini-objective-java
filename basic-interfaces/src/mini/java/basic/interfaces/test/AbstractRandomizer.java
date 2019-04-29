package mini.java.basic.interfaces.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates random object for generic type.
 * To implement calss you need to provide next() method to generate new rendom object ot given type.
 *
 * @see AbstractRandomizer#next(int)
 * @param <T> Type
 */
public abstract class AbstractRandomizer<T> implements TypeAware<T> {
    protected Random random;
    long seed = 0;

    /**
     * Returns new random object of type T
     * <B>This is only method you need to implement</B>
     *
     * @param limit Length of the object
     * @return New random object
     *
     */

    @Deprecated
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
