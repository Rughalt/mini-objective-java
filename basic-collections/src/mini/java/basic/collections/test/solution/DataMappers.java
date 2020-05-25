package mini.java.basic.collections.test.solution;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataMappers {
    /**
     * Maps line of data to @SimpleDataRepository.Product
     * @param objects Data loaded from DataLoader
     * @return Object[] parsed into @SimpleDataRepository.Product
     * @throws ProductDataMalformedException thrown if data is malformed/cannot be parsed
     */
    public static SimpleDataRepository.Product mapToProduct(Object[] objects) throws ProductDataMalformedException {
        if (objects.length != 5) throw new ProductDataMalformedException();
        try {
            return new SimpleDataRepository.Product(Integer.parseInt(objects[1].toString()), objects[2].toString(), Double.parseDouble(objects[3].toString()), Boolean.getBoolean(objects[4].toString()));
        } catch (Exception e) {
            throw new ProductDataMalformedException();
        }
    }


    /**
     * Maps line of data to @SimpleDataRepository.Warehouse
     * @param objects Data loaded from DataLoader
     * @return Object[] parsed into @SimpleDataRepository.Warehouse
     * @throws WarehouseDataMalformedException thrown if data is malformed/cannot be parsed
     */
    public static SimpleDataRepository.Warehouse mapToWarehouse(Object[] objects) throws WarehouseDataMalformedException {
        if (objects.length < 4) throw new WarehouseDataMalformedException();
        try {
            return new SimpleDataRepository.Warehouse(Integer.parseInt(objects[1].toString()), objects[2].toString(), Boolean.parseBoolean(objects[3].toString()), objects.length == 4 ? List.of() : Arrays.stream(objects[4].toString().split(",")).map(Integer::parseInt).collect(Collectors.toList()));

        } catch (Exception e) {
            throw new WarehouseDataMalformedException();
        }
    }
}
