package mini.java.basic.collections.test;

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
        return null;
    }


    /**
     * Maps line of data to @SimpleDataRepository.Warehouse
     * @param objects Data loaded from DataLoader
     * @return Object[] parsed into @SimpleDataRepository.Warehouse
     * @throws WarehouseDataMalformedException thrown if data is malformed/cannot be parsed
     */
    public static SimpleDataRepository.Warehouse mapToWarehouse(Object[] objects) throws WarehouseDataMalformedException {
        return null;
    }
}
