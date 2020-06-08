package mini.java.basic.collections;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CollectionsTest {


    @org.junit.Test
    public void listAdd() {
        List<String> l = new ArrayList<>();
        l.add("a");
        l.addAll(List.of("b","c"));
        assertEquals(l, List.of("a","b","c"));
    }

    @org.junit.Test
    public void listRemove() {
        List<String> list = new ArrayList<>(List.of("a", "b", "b"));
        boolean removed = list.remove("c");
        assertEquals(list, List.of("a","b","b"));
        assertFalse(removed);
    }

    @org.junit.Test
    public void listRemoveDuplicated() {
        List<String> list = new ArrayList<>(List.of("a", "b", "b"));
        boolean removed = list.remove("b");
        assertEquals(list, List.of("a","b"));
        assertTrue(removed);
    }

    @org.junit.Test
    public void listRemoveAll() {
        List<String> list = new ArrayList<>(List.of("a", "b", "b"));
        while (list.remove("b")) { }
        assertEquals(list, List.of("a"));
    }

    @org.junit.Test
    public void listRemoveAllStream() {
        List<String> list = new ArrayList<>(List.of("a", "b", "b"));
        List<String> l = list.stream()
                             .filter(element -> !element.equals("b"))
                             .collect(Collectors.toList());
        List<String> l2 = new ArrayList<>();

        assertEquals(l, List.of("a"));
    }

    @org.junit.Test
    public void listSort() {
        List<String> l = new ArrayList<>();
        l.add("c");
        l.addAll(List.of("b","a"));
        l.sort(Comparator.naturalOrder());
        assertEquals(l, List.of("a","b","c"));
    }

    @org.junit.Test
    public void setAdd() {
        Set<String> linkedHashSet = new LinkedHashSet<>(List.of("a", "b", "d"));
        Set<String> hashSet = new HashSet<>(List.of("a", "b", "d"));
        System.out.print("Hashset: ");
        hashSet.forEach(s -> System.out.printf("%s,", s));
        System.out.println();
        System.out.print("LinkedHashSet: ");
        for (String s1 : linkedHashSet) {
            System.out.printf("%s,", s1);
        }
        System.out.println();
        hashSet.add("c");
        linkedHashSet.add("c");
        hashSet.add("d");
        linkedHashSet.add("d");
        System.out.print("Hashset: ");
        hashSet.forEach(s1 -> System.out.printf("%s,", s1));
        System.out.println();
        System.out.print("LinkedHashSet: ");
        linkedHashSet.forEach(s -> System.out.printf("%s,", s));
    }



    @org.junit.Test
    public void mapPut() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a","string a");
        hashMap.put("a","string b");
        assertEquals(hashMap.get("a"),"string b");

        hashMap.putIfAbsent("a","string c");
        assertEquals(hashMap.get("a"),"string b");
    }

    @org.junit.Test
    public void mapGet() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a","string a");
        hashMap.put("b","string b");

        assertEquals(hashMap.get("a"),"string a");
        assertFalse(hashMap.containsKey("c"));
        assertEquals(hashMap.getOrDefault("c","trelemorele"),"trelemorele");
    }

    @org.junit.Test
    public void mapComplexType() {
        Map<Class, Printer> hashMap = new HashMap<>();
        hashMap.put(String.class, new StringPrinter());
        hashMap.put(Integer.class, new IntegerPrinter());
        hashMap.put(Number.class, new NumberPrinter());


        Integer a = 3;
        hashMap.getOrDefault(a.getClass(), new DefaultLogger()).print(a);
        Object b = new String("3");
        hashMap.getOrDefault(b.getClass(), new DefaultLogger()).print(b);
        Object c = new ArrayList<>();
        hashMap.getOrDefault(c.getClass(), new DefaultLogger()).print(c);

        Double d = 3.0;
        Printer printer = hashMap.get(d.getClass());
        if (printer != null) {
            printer.print(d);
        }
    }

    private interface Printer<T> {
        void print(T object);
    }

    private class StringPrinter implements Printer<String> {
        @Override
        public void print(String v) {
            System.out.println("String: "+ v);
        }
    }

    private class IntegerPrinter implements Printer<Integer> {
        @Override
        public void print(Integer v) {
            System.out.println("Integer: "+ v);
        }
    }

    private class NumberPrinter implements Printer<Number> {
        @Override
        public void print(Number v) {
            System.out.println("Number: "+ v);
        }
    }


    private class DefaultLogger implements Printer<Object> {
        @Override
        public void print(Object object) {
            System.out.println("Default: " + object.toString());
        }
    }

    @org.junit.Test
    public void exceptionTest() {
        Map<Class, Printer> hashMap = new HashMap<>();
        hashMap.put(String.class, new StringPrinter());

        Double d = 3.0;
        try {
            printForObject(hashMap, d);
        } catch (StupidException e) {
            System.out.println("Catched!");
        }


    }


    private void printForObject(Map<Class, Printer> hashMap, Object d) throws StupidException {
        try {
            hashMap.get(d.getClass()).print(d);
        } catch (NullPointerException e) {

            new DefaultLogger().print(String.format("Exception ifnored for %s", d.getClass().toGenericString()));
            // Throws RuntimeException does not need to be handled
            // throw new RuntimeException();
            // Throw normal Exception, needs to be either try{}/catched{} or added to method definition (throws)
            // throw new StupidException(List.of("stupid"));
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finally");
        }
    }

    private class StupidException extends Exception {
        private List<String> a;
        public StupidException(List<String> a) {
            this.a = a;
        }
    }


}
