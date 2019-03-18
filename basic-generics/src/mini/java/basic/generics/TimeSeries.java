package mini.java.basic.generics;

import java.lang.reflect.Array;

public class TimeSeries<T> {
    private final String name;
    private final T[] data;

    public TimeSeries(String name, T... data) {
        this.name = name;
        this.data = data;
    }

    public static <T> TimeSeries<T> empty(String name, Integer length, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        TimeSeries<T> timeSeries = new TimeSeries<>(name, (T[]) Array.newInstance(clazz, length));

        return timeSeries;
    }

    public String getName() {
        return name;
    }

    public T first() {
        if (data == null)
            throw new IndexOutOfBoundsException();
        return data[0];
    }

    public T last() {
        if (data == null)
            throw new IndexOutOfBoundsException();
        return data[data.length - 1];
    }

    public <R> boolean nameEquals(TimeSeries<R> that) {
        return this.getName().equals(that.getName());
    }



}
