package mini.java.basic.generics;

import java.util.List;

public class Dataset<E extends Integer, R extends TimeSeries<E>> {
    private List<R> timeSeries;

    public Dataset(List<R> timeSeries) {
        this.timeSeries = timeSeries;
    }

    public E[] getFirstData() {
        return timeSeries.get(0).getData();
    }
}
