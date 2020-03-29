package mini.java.basic.arrays.testsolution;

import java.util.Arrays;

public class AnonymousTimeSeries extends TimeSeries {
    public AnonymousTimeSeries(int... data) {
        super(null, data);
    }

    @Override
    public String toString() {
        return "AnonymousTimeSeries{" +
                "data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    public boolean isAnonymous() {
        return true;
    }
}
