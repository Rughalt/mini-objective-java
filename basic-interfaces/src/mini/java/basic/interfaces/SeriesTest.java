package mini.java.basic.interfaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class SeriesTest {

    @org.junit.Test
    public void integerSeriesTest() {
        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(3);
        IntegerTimeSeries i = new IntegerTimeSeries("Name",data);
        assertEquals(i.get(1),Integer.valueOf(3));
        assertEquals(i.sum(), Integer.valueOf(4));
    }

    @org.junit.Test
    public void stringSeriesTest() {
        List<String> data = new ArrayList<>();
        data.add("Ala");
        data.add("Makota");
        StringTimeSeries i = new StringTimeSeries("Name",data);
        assertEquals(i.get(1),"Makota");
        assertEquals(i.sum(), "AlaMakota");
    }

    @org.junit.Test
    public void stringSeriesConsoleInterfacetTest() {
        List<String> data = new ArrayList<>();
        data.add("Ala");
        data.add("Makota");
        StringTimeSeries i = new StringTimeSeries("Name",data);
        ((ConsolePrintable)i).printToSystemOut();
    }
}
