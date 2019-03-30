package mini.java.basic.interfaces;

import java.util.List;

public abstract class  AbstractTimeSeries<T> {
    List<T> data;


    String name;

    public AbstractTimeSeries(String name, List<T> data) {
        this.name = name;
        this.data = data;
    }


    // Dodaje dane do data
    public void add(T o) {
        data.add(o);
    }

    // Usuwa daną z data
    public void remove(Integer i) {
        data.remove(i);
    }

    // Zwraca obiekt o odpowiednim indeksie
    public T get(Integer i) {
        return data.get(i);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Liczy sumę, abstrakcyjna
    public abstract T sum();
}
