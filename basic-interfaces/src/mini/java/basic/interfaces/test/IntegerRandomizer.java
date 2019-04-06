package mini.java.basic.interfaces.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa IntegerRandomizer generuje nowe, losowe liczby całkowite w podanym przedziale
 */
public class IntegerRandomizer extends AbstractRandomizer<Integer> implements TypeAware<Integer> {
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



    @Override
    public Integer getEmpty() {
        return 0;
    }

    @Override
    public Class getElementClass() {
        return Integer.class;
    }
}
