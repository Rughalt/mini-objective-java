package mini.java.basic.interfaces;

import java.util.List;

public class StringTimeSeries extends AbstractTimeSeries<String> implements Printable, Summable<String> {
    public StringTimeSeries(String name, List<String> data) {
        super(name, data);
    }

    @Override
    public String createElementInstance() {
        return "";
    }

    @Override
    public String sum() {
        StringBuilder sb = new StringBuilder();
        for (String el : data)
            sb.append(el);
        return sb.toString();
    }

    @Override
    public void printToSystemOut() {
        data.forEach(d -> System.out.println(d));
    }
}
