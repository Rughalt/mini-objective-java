package mini.java.basic.interfaces;

import java.util.List;

public class IntegerTimeSeries extends AbstractTimeSeries<Integer> implements Summable<Integer>{


    public IntegerTimeSeries(String name, List<Integer> data) {
        super(name, data);
    }

    @Override
    public Integer createElementInstance() {
        return 0;
    }

    @Override
    public Integer sum() {
        return data.stream().mapToInt(Integer::intValue).sum();
    }
}
