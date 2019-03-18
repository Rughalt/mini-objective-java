package mini.java.basic.generics;

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
        assertEquals(timeSeriesFloat.first(), "a");
        assertEquals(timeSeriesFloat.last(), "c");
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
        assertTrue(timeSeriesInteger.nameEquals(timeSeriesFloat));
        assertTrue(timeSeriesFloat.nameEquals(timeSeriesInteger));
    }


    @org.junit.Test
    public void testDataset() {
        Dataset<Integer, TimeSeries<Integer>> d = new Dataset<Integer,TimeSeries<Integer>>(List.of(TimeSeries.empty("empty",1,Integer.class)));
    }


    @org.junit.Test(expected = ClassCastException.class)
    public void testDatasetNoType() {
        Dataset d = new Dataset(List.of(TimeSeries.empty("empty",1,Float.class)));
        Integer[] data = d.getFirstData();
    }
}
