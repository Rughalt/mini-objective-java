package mini.java.basic.interfaces.test;

import java.util.ArrayList;
import java.util.List;

public class IntegerList extends ArrayList<Integer> implements TypeAware<Integer> {
    public Integer getEmpty() {
        return 0;
    }

    public Class getElementClass() {
        return Integer.class;
    }
}
