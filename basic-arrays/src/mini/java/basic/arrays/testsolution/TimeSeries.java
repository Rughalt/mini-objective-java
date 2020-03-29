package mini.java.basic.arrays.testsolution;

import java.util.Arrays;
import java.util.Objects;

public class TimeSeries {
    private final String name;
    protected final int[] data;

    public TimeSeries(String name, int... data) {
        this.name = name;
        this.data = data;
    }

    public double average() {
        double sum = 0.0;
        for (int i = 0; i < data.length; i++) {
            sum+=data[i];
        }
        return sum/data.length;
    }

    public int getMaximum() {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > maxValue) maxValue = data[i];
        }
        return maxValue;
    }

    public String getName() {
        return name;
    }

    public int[] getData() {
        return data;
    }

    public boolean isAnonymous() {
        return false    ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSeries that = (TimeSeries) o;
        return Objects.equals(name, that.name) &&
                Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "TimeSeries{" +
                "name='" + name + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
