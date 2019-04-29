package mini.java.basic.interfaces.test;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

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
        AbstractRandomizer<Integer> integerRandomizer = new IntegerRandomizer();
        AbstractRandomizer<String> stringRandomizer = new StringRandomizer();
        //TODO: asdasdasdsa
        for (int i = 0; i < 1000; i++) {
            Integer testedInteger = integerRandomizer.next(10);
            assertTrue(testedInteger >= 0 && testedInteger < 10);
            String testedString = stringRandomizer.next(10);
            assertEquals(10, testedString.length());
        }

    }

    public void nextList() {
        AbstractRandomizer<Integer> integerRandomizer = new IntegerRandomizer();
        AbstractRandomizer<String> stringRandomizer = new StringRandomizer();

        assertTrue(integerRandomizer.nextList(1000,5).stream().allMatch(i -> i >= 0 && i < 5));
        assertTrue(stringRandomizer.nextList(1000,5).stream().allMatch(s -> s.length() == 5));
    }

    @Test
    public void nextArray() {
        AbstractRandomizer<Integer> integerRandomizer = new IntegerRandomizer();
        AbstractRandomizer<String> stringRandomizer = new StringRandomizer();

        List<AbstractRandomizer> l = List.of(integerRandomizer,stringRandomizer);

        assertEquals(10,integerRandomizer.nextArray(10,5).length);
        assertEquals(7,stringRandomizer.nextArray(7,7).length);
    }

    @Test
    public void emptyObjectTest() {
        TypeAware integerRandomizer = new IntegerRandomizer();
        TypeAware stringRandomizer = new StringRandomizer();
        TypeAware integerList = new IntegerList();

        assertEquals(0, integerList.getEmpty());
        assertEquals(0, integerRandomizer.getEmpty());
        assertEquals("", stringRandomizer.getEmpty());
    }

    @Test
    public void elementClassTest() {
        TypeAware integerRandomizer = new IntegerRandomizer();
        TypeAware stringRandomizer = new StringRandomizer();
        TypeAware integerList = new IntegerList();

        assertEquals(Integer.class, integerList.getElementClass());
        assertEquals(Integer.class, integerRandomizer.getElementClass());
        assertEquals(String.class, stringRandomizer.getElementClass());
    }
}