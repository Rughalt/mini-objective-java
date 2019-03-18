package mini.java.basic.generics;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class GenericsTest {
    @org.junit.Test
    public void timeSeriesIntegerConstructor() {
        TimeSeries<Integer> timeSeriesInteger = new TimeSeries<>("TS1",1,2,3,4);
        assertEquals(timeSeriesInteger.first(), Integer.valueOf(1));
        assertEquals(timeSeriesInteger.last(), Integer.valueOf(4));
    }


    @org.junit.Test
    public void timeSeriesStringConstructor() {
        TimeSeries<String> timeSeriesString = new TimeSeries<>("TS1","a","b","c");
        assertEquals(timeSeriesString.first(), "a");
        assertEquals(timeSeriesString.last(), "c");
    }

    @org.junit.Test
    public void timeSeriesStringFromMethod() {
        TimeSeries<String> timeSeriesString = TimeSeries.empty("empty",1,String.class);
        assertEquals(timeSeriesString.first(), null);
        assertEquals(timeSeriesString.last(), null);
    }

    @org.junit.Test
    public void timeSeriesIntegerFromMethod() {
        TimeSeries<Integer> timeSeriesInteger = TimeSeries.empty("empty",1,Integer.class);
        assertEquals(timeSeriesInteger.first(), null);
        assertEquals(timeSeriesInteger.last(), null);
    }

    @org.junit.Test
    public void timeSeriesNameEquality() {
        TimeSeries<Integer> timeSeriesInteger = TimeSeries.empty("empty",1,Integer.class);
        TimeSeries<String> timeSeriesString = TimeSeries.empty("empty",1,String.class);
        assertTrue(timeSeriesInteger.nameEquals(timeSeriesString));
        assertTrue(timeSeriesString.nameEquals(timeSeriesInteger));
    }
}
