package mini.java.basic.generics;

// Lista TimeSeries<E> od Number
// E sum()

import java.util.List;

public class SeriesSummer<E extends Number,R extends TimeSeries<E>> {
    private List<R> l;

    public SeriesSummer(List<R> l) {
        this.l = l;
    }

    public E sum() {
        //code
        E sum = null;
        for (R ts :
                l) {
            for (E d :
                    ts.getData()) {
                if (sum == null)
                    sum = d;
                else
                    sum = (E) addNumbers(sum,d);
            }
        }
        return sum;
    }

    public static Number addNumbers(Number a, Number b) {
        if(a instanceof Double || b instanceof Double) {
            return a.doubleValue() + b.doubleValue();
        } else if(a instanceof Float || b instanceof Float) {
            return a.floatValue() + b.floatValue();
        } else if(a instanceof Long || b instanceof Long) {
            return a.longValue() + b.longValue();
        } else {
            return a.intValue() + b.intValue();
        }
    }
}
