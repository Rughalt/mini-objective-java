package mini.java.basic.interfaces.test;

import org.junit.Test;

import static org.junit.Assert.*;

/*
    Testy dla klas IntegerRandomizer, StringRandomizer
    Zadanie:
    1. Zaproponuj i wydziel z klas interfejs/klasy abstrakcyjną
    2. Przekonwertuj testy tak aby tam gdzie to możliwe korzystały z nowych obiektów
 */
public class RandomizerTests {

    @Test
    public void next() {
        IntegerRandomizer integerRandomizer = new IntegerRandomizer();
        StringRandomizer stringRandomizer = new StringRandomizer();

        for (int i = 0; i < 1000; i++) {
            Integer testedInteger = integerRandomizer.next(10);
            assertTrue(testedInteger >= 0 && testedInteger < 10);
            String testedString = stringRandomizer.next(10);
            assertEquals(10, testedString.length());
        }

    }

    @Test
    public void nextList() {
        IntegerRandomizer integerRandomizer = new IntegerRandomizer();
        StringRandomizer stringRandomizer = new StringRandomizer();

        assertTrue(integerRandomizer.nextList(1000,5).stream().allMatch(i -> i >= 0 && i < 5));
        assertTrue(stringRandomizer.nextList(1000,5).stream().allMatch(s -> s.length() == 5));
    }

    @Test
    public void nextArray() {
        IntegerRandomizer integerRandomizer = new IntegerRandomizer();
        StringRandomizer stringRandomizer = new StringRandomizer();

        assertEquals(10,integerRandomizer.nextArray(10,5).length);
        assertEquals(7,stringRandomizer.nextArray(7,7).length);
    }

    @Test
    public void emptyObjectTest() {
        IntegerRandomizer integerRandomizer = new IntegerRandomizer();
        StringRandomizer stringRandomizer = new StringRandomizer();
        IntegerList integerList = new IntegerList();

        assertEquals(Integer.valueOf(0), integerList.getEmpty());
        assertEquals(Integer.valueOf(0), integerRandomizer.getEmpty());
        assertEquals("", stringRandomizer.getEmpty());
    }

    @Test
    public void elementClassTest() {
        IntegerRandomizer integerRandomizer = new IntegerRandomizer();
        StringRandomizer stringRandomizer = new StringRandomizer();
        IntegerList integerList = new IntegerList();

        assertEquals(Integer.class, integerList.getElementClass());
        assertEquals(Integer.class, integerRandomizer.getElementClass());
        assertEquals(String.class, stringRandomizer.getElementClass());
    }
}