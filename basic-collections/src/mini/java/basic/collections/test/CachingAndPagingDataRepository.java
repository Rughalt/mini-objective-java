package mini.java.basic.collections.test;

import java.util.*;

/**
 * Caching and paging data repository
 *
 * Cache can be implemented in any *sensible* way (this means using maps). Cache should have
 * ability to retrieve both full datasets (for both entities Product and Warehouse)
 * and single elements by id (this is enforced by tests).
 *
 * Hints:
 * - Basic map functionality was showcased in labs before
 * - You don't have to have only one map for caching
 * - Some methods are simplet o implement, some harder - as
 *   each is worth the same number of points, if you get stuck,
 *   see if you can implement next one.
 * - Think about what functions will need to clear caches and how.
 *
 * Scoring:
 * - Each passed test (with correct implementation)
 *   is worth 10% of possible score (2,5 points)
 * @see DataRepositoryTest
 *
 * Entities:
 * @see mini.java.basic.collections.test.SimpleDataRepository.Product
 * @see mini.java.basic.collections.test.SimpleDataRepository.Warehouse
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

    /***
     * Returns all products from database. Can be used to pre-populate single element
     * cache (for use in getProductById(id)). Can also be used as a helper function in
     * other methods.
     * @see CachingAndPagingDataRepository#getProductsById(int)
     * @return List of all products
     */
    public Collection<SimpleDataRepository.Product> getAllProducts() {
        return dataRepository.getProducts();
    }

    /***
     * Retrieves product from database. In best case scenario should be aware of getAllProducts() method.
     * @see CachingAndPagingDataRepository#getAllProducts()
     * @param i id of product to retrieve
     * @return Retrieved product
     */
    public SimpleDataRepository.Product getProductsById(int i) {
        return dataRepository.getProductById(i);
    }

    /***
     * Returns all products from database, sorted by name.
     * @return List of products
     */
    public Collection<SimpleDataRepository.Product> getAllProductsSortedByName() {
        return dataRepository.getProducts();
    }

    /***
     * Returns all products from database, filtered by expires true sorted by name.
     * @return List of products
     */
    public Collection<SimpleDataRepository.Product> getAllProductsFilteredByExpiresTrueAndSortedByName() {
        return dataRepository.getProducts();
    }

    /***
     * Returns page of products from database. Should behave gracefully - return empty
     * list - if page is out of bounds.
     * @param page Page number
     * @param pagesize Size of page
     * @return List of products
     */
    public Collection<SimpleDataRepository.Product> getProductsPage(int page, int pagesize) {
        return dataRepository.getProducts();
    }

    /***
     * Returns all warehouses from database. Should fill products field in each retrieved warehouse element
     * @see CachingAndPagingDataRepository#getProductsById(int)
     * @see mini.java.basic.collections.test.SimpleDataRepository.Warehouse#products
     * @return List of all warehouses
     */
    public Collection<SimpleDataRepository.Warehouse> getAllWarehouses() {
        return dataRepository.getWarehouses();
    }


    /**
     * Updates product in database
     * @param i Id of product to update
     * @param product Product data to save to database
     */
    public void updateProduct(int i, SimpleDataRepository.Product product) {
        dataRepository.updateProduct(i, product);
    }

    /**
     * Upserts product in database - inserts if id is missing, updates if id exists in database
     * @param product Product data to save to database
     */
    public void upsertProduct(SimpleDataRepository.Product product) {
        dataRepository.upsertProduct(product);
    }




}
