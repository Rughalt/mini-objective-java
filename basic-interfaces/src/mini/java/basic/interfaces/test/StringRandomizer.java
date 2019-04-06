package mini.java.basic.interfaces.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa StringRandomizer generuje nowe, losowe napisy całkowite o konkretnej długości
 */
public class StringRandomizer extends AbstractRandomizer<String> implements TypeAware<String> {

    public StringRandomizer() {
        random = new Random();
    }

    public StringRandomizer(long seed) {
        this.seed = seed;
        random = new Random(seed);
    }

    @Override
    public String next(int limit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }


    public String getEmpty() {
        return new String();
    }

    public Class getElementClass() {
        return String.class;
    }
}
