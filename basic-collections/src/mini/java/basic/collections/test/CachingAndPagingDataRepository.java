package mini.java.basic.collections.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Caching and paging data repository
 *
 * Cache can be implemented in any *sensible* way.
 */
public class CachingAndPagingDataRepository {
    private SimpleDataRepository dataRepository = new SimpleDataRepository();

    /**
     * This function should not be changed!!!
     * @return Returns number of calls registered by fake database engine
     */
    public int getRepositoryCalls() {
        return dataRepository.getCalls();
    }


    private Map<DatabaseRequest, Collection<SimpleDataRepository.Warehouse>> warehouseRequestCache = new HashMap<>();
    private Map<DatabaseRequest, Collection<SimpleDataRepository.Product>> productRequestCache = new HashMap<>();

    /***
     * Returns all products from database. Can be used to pre-populate single element
     * cache (for use in getProductById(id)). Can also be used as a helper function
     * @see CachingAndPagingDataRepository#getProductsById(int)
     * @return List of all products
     */
    public Collection<SimpleDataRepository.Product> getAllProducts() {
        DatabaseRequest databaseRequest = new DatabaseRequest(DatabaseRequest.Type.ALL, null);
        if (!productRequestCache.containsKey(databaseRequest)) {
            Collection<SimpleDataRepository.Product> productsFromDatabase = dataRepository.getProducts();
            productRequestCache.put(databaseRequest, productsFromDatabase);
            productsFromDatabase.forEach(product -> productRequestCache.put(new DatabaseRequest(DatabaseRequest.Type.ID, product.getId()), List.of(product)));
        }
        return productRequestCache.get(databaseRequest);
    }

    /***
     * Returns all warehouses from database. Should fill products field in each retrieved warehouse element
     * @see CachingAndPagingDataRepository#getProductsById(int)
     * @see mini.java.basic.collections.test.SimpleDataRepository.Warehouse#products
     * @return List of all warehouses
     */
    public Collection<SimpleDataRepository.Warehouse> getAllWarehouses() {
        DatabaseRequest databaseRequest = new DatabaseRequest(DatabaseRequest.Type.ALL, null);
        if (!warehouseRequestCache.containsKey(databaseRequest)) {
            Collection<SimpleDataRepository.Warehouse> warehousesFromDatabase = dataRepository.getWarehouses();
            warehousesFromDatabase.forEach(warehouse -> {
                List<SimpleDataRepository.Product> products = new ArrayList<>();
                warehouse.getProductIds().forEach(productId -> {
                    products.add(getProductsById(productId));
                });
                warehouse.setProducts(products);
            });
            warehouseRequestCache.put(databaseRequest, warehousesFromDatabase);
        }
        return warehouseRequestCache.get(databaseRequest);
    }

    /***
     * Retrieves product from database. In best case scenario should be aware of getAllProducts() method.
     * @see CachingAndPagingDataRepository#getAllProducts()
     * @param i id of product to retrieve
     * @return Retrieved product
     */
    public SimpleDataRepository.Product getProductsById(int i) {
        DatabaseRequest databaseRequest = new DatabaseRequest(DatabaseRequest.Type.ID, i);
        if (!productRequestCache.containsKey(databaseRequest)) productRequestCache.put(databaseRequest, List.of(dataRepository.getProductById(i)));
        return productRequestCache.get(databaseRequest).toArray(new SimpleDataRepository.Product[1])[0];
    }

    /***
     * Returns all products from database, sorted by name.
     * @return List of products
     */
    public Collection<SimpleDataRepository.Product> getAllProductsSortedByName() {
        List<SimpleDataRepository.Product> productsFromDatabase = new ArrayList<>(getAllProducts());
        productsFromDatabase.sort(Comparator.comparing(SimpleDataRepository.Product::getName, String::compareTo));
        return productsFromDatabase;
    }

    /***
     * Returns all products from database, filtered by expires true sorted by name.
     * @return List of products
     */
    public Collection<SimpleDataRepository.Product> getAllProductsFilteredByExpiresTrueAndSortedByName() {
        List<SimpleDataRepository.Product> productsFromDatabase = new ArrayList<>(getAllProducts());
        return productsFromDatabase.stream().filter(SimpleDataRepository.Product::isExpires).sorted(Comparator.comparing(SimpleDataRepository.Product::getName, String::compareToIgnoreCase)).collect(Collectors.toList());
    }

    /**
     * Updates product in database
     * @param i Id of product to update
     * @param product Product data to save to database
     */
    public void updateProduct(int i, SimpleDataRepository.Product product) {
        dataRepository.updateProduct(i, product);
        productRequestCache.clear();
        warehouseRequestCache.clear();
    }

    /**
     * Upserts product in database - insterts if id is missing, updates if id exists in database
     * @param product Product data to save to database
     */
    public void upsertProduct(SimpleDataRepository.Product product) {
        if (dataRepository.upsertProduct(product)) {
            productRequestCache.clear();
        } else {
            // We clean all request, since nothing else changed (for sure)
            productRequestCache.remove(new DatabaseRequest(DatabaseRequest.Type.ALL, null));
        }
    }

    /***
     * Returns page of products from database. Should behave gracefully - return empty
     * list - if page is out of bounds.
     * @param page Page number
     * @param pagesize Size of page
     * @return List of products
     */
    public Collection<SimpleDataRepository.Product> getProductsPage(int page, int pagesize) {
        List<SimpleDataRepository.Product> productsFromDatabase = new ArrayList<>(getAllProducts());
        try {
            return productsFromDatabase.subList(page * pagesize, (page + 1) * pagesize);
        } catch (IndexOutOfBoundsException iob) {
            return new ArrayList<>();
        }
    }

    private static class DatabaseRequest {
        private final Type requestType;
        private final Integer additionalProperty;

        public DatabaseRequest(Type requestType, Integer additionalProperty) {
            this.requestType = requestType;
            this.additionalProperty = additionalProperty;
        }

        public enum Type { ALL, ID }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DatabaseRequest that = (DatabaseRequest) o;
            return requestType == that.requestType &&
                    Objects.equals(additionalProperty, that.additionalProperty);
        }

        @Override
        public int hashCode() {
            return Objects.hash(requestType, additionalProperty);
        }
    }
}
