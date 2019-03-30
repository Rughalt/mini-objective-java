package mini.java.basic.generics;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class GenericsTest {
    @org.junit.Test
    public void timeSeriesIntegerConstructor() {
        TimeSeries<Integer> timeSeriesInteger = new TimeSeries<Integer>("TS1",1,2,3,4);
        assertEquals(timeSeriesInteger.first(), Integer.valueOf(1));
        assertEquals(timeSeriesInteger.last(), Integer.valueOf(4));
    }

    @org.junit.Test
    public void timeSeriesStringConstructor() {
        TimeSeries<Double> timeSeriesFloat = new TimeSeries<Double>("TS1",1.0,2.0,3.0);
        assertEquals(timeSeriesFloat.first(), Double.valueOf(1.0));
        assertEquals(timeSeriesFloat.last(), Double.valueOf(3.0));
    }



    @org.junit.Test
    public void timeSeriesIntegerFromMethod() {
        TimeSeries<Integer> timeSeriesInteger = TimeSeries.empty("empty",1,Integer.class);
        assertNull(timeSeriesInteger.first());
        assertNull(timeSeriesInteger.last());
    }

    @org.junit.Test
    public void timeSeriesNameEquality() {
        TimeSeries<Integer> timeSeriesInteger = TimeSeries.empty("empty",1,Integer.class);
        TimeSeries<Float> timeSeriesFloat = TimeSeries.empty("empty",1,Float.class);
        assertTrue(timeSeriesInteger.<Float>nameEquals(timeSeriesFloat));
        assertTrue(timeSeriesFloat.nameEquals(timeSeriesInteger));
    }


    @org.junit.Test
    public void testDataset() {
        List<TimeSeries<Integer>> l = new ArrayList<>();
        l.add(new TimeSeries<>("TS1", 1, 2, 3, 4));
        Dataset<Integer, TimeSeries<Integer>> d = new Dataset<>(l);
    }


    @org.junit.Test(expected = ClassCastException.class)
    public void testDatasetNoType() {
        List<TimeSeries<Double>> l = new ArrayList<>();
        l.add(new TimeSeries<Double>("TS1",1.0,2.0,3.0,4.0));
        Dataset d = new Dataset(l);
        Integer[] data = d.getFirstData();
    }

    @org.junit.Test
    public void summerTest() {
        List<TimeSeries<Integer>> l = new ArrayList<>();
        l.add(new TimeSeries<>("TS1", 1, 2, 3, 4));
        l.add(new TimeSeries<>("TS1", 1, 2, 3, 4));
        SeriesSummer<Integer, TimeSeries<Integer>> d = new SeriesSummer<>(l);
        assertEquals(d.sum(),Integer.valueOf(20));
    }
}
