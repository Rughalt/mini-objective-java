package mini.java.basic.collections.test.solution;

import org.junit.Assert;

import java.io.IOException;
import java.util.Collection;

public class DataRepositoryTest {

    /***
     * Checks if cache is implemented correctly for simple element type
     * @difficulty 1
     */
    @org.junit.Test(timeout = 1800)
    public void getAllProducts() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();

        // Inital get data
        Collection<SimpleDataRepository.Product> data = dataRepository.getAllProducts();

        // Get data again
        data = dataRepository.getAllProducts();

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 1);
        Assert.assertEquals(data.size(), 9);
    }

    /***
     * Checks if DataLoader and Mapper throw good Exception
     * @difficulty 1
     */
    @org.junit.Test(timeout = 1800, expected = ProductDataMalformedException.class)
    public void tryGetMalformedProducts() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        SimpleDataRepository simpleDataRepository = new SimpleDataRepository("dataset_malformed_product.csv");
    }

    /***
     * Checks if DataLoader and Mapper throw good Exception
     * @difficulty 1
     */
    @org.junit.Test(timeout = 1800, expected = WarehouseDataMalformedException.class)
    public void tryGetMalformedWarehouse() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        SimpleDataRepository simpleDataRepository = new SimpleDataRepository("dataset_malformed_warehouse.csv");
    }

    /***
     * Checks if DataLoader and Mapper throw good Exception
     * @difficulty 1
     */
    @org.junit.Test(timeout = 1800, expected = NoDataException.class)
    public void tryGetEmptyProduct() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        SimpleDataRepository simpleDataRepository = new SimpleDataRepository("dataset_empty_product.csv");
    }

    /***
     * Checks if DataLoader bubbles IOException
     * @difficulty 1
     */
    @org.junit.Test(timeout = 1800, expected = IOException.class)
    public void tryGetNonextistingFile() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        SimpleDataRepository simpleDataRepository = new SimpleDataRepository("dataset_thereisnothing.csv");
    }


    /***
     * Checks if cache is implemented correctly for single elements
     * @difficulty 1
     */
    @org.junit.Test(timeout = 2800)
    public void getProductsById() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();
        int randomProductId = (int)Math.floor(Math.random() * 6) + 2;
        // Inital get data
        SimpleDataRepository.Product product1 = dataRepository.getProductsById(1);
        SimpleDataRepository.Product product2 = dataRepository.getProductsById(randomProductId);

        // Get data again
        product1 = dataRepository.getProductsById(1);
        product2 = dataRepository.getProductsById(randomProductId);

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 2);
        Assert.assertNotNull(product1);
        Assert.assertNotNull(product2);
        Assert.assertEquals(1, product1.getId());
        Assert.assertEquals(randomProductId, product2.getId());
    }

    /***
     * Checks if cache is optimized for retrieving single elements when all
     * elements were requested before.
     * @difficulty 2
     */
    @org.junit.Test(timeout = 1800)
    public void getAllProductsAndProductsByIdOptimization() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();
        int randomProductId = (int)Math.floor(Math.random() * 6) + 2;
        // Inital get data
        Collection<SimpleDataRepository.Product> data = dataRepository.getAllProducts();
        SimpleDataRepository.Product product2 = dataRepository.getProductsById(randomProductId);

        // Get data again
        data = dataRepository.getAllProducts();
        product2 = dataRepository.getProductsById(randomProductId);

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 1);
        Assert.assertNotNull(product2);
        Assert.assertEquals(9, data.size());
        Assert.assertEquals(randomProductId, product2.getId());
    }

    /***
     * Checks if cache extensions (sorting data from repository) is implemented correctly
     * @difficulty 2
     */
    @org.junit.Test(timeout = 1800)
    public void getAllProductsSortedName() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();

        // Inital get data
        Collection<SimpleDataRepository.Product> data = dataRepository.getAllProductsSortedByName();

        // Get data again
        data = dataRepository.getAllProductsSortedByName();

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 1);
        Assert.assertArrayEquals(new int[]{1,4,5,3,7,8,2,9,6}, data.stream().mapToInt(SimpleDataRepository.Product::getId).toArray());
    }

    /***
     * Checks if complex function with filtering and sorting
     * @difficulty 2
     */
    @org.junit.Test(timeout = 1800)
    public void getAllProductsFilteredByExpiresTrueAndSortedName() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();

        // Inital get data
        Collection<SimpleDataRepository.Product> data = dataRepository.getAllProductsFilteredByExpiresTrueAndSortedByName();

        // Get data again
        data = dataRepository.getAllProductsFilteredByExpiresTrueAndSortedByName();

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 1);
        Assert.assertArrayEquals(new int[]{1,5,3,2,9,6}, data.stream().mapToInt(SimpleDataRepository.Product::getId).toArray());
    }

    /***
     * Checks if cache extensions (paging data from repository) is implemented correctly
     * @difficulty 2
     */
    @org.junit.Test(timeout = 1800)
    public void getProductsPage() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();

        // Inital get data
        Collection<SimpleDataRepository.Product> page1 = dataRepository.getProductsPage(0,3);
        Collection<SimpleDataRepository.Product> page2 = dataRepository.getProductsPage(5,3);
        Collection<SimpleDataRepository.Product> page5 = dataRepository.getProductsPage(5,3);

        // Get data again
        page1 = dataRepository.getProductsPage(0,3);
        page2 = dataRepository.getProductsPage(1,3);
        page5 = dataRepository.getProductsPage(5,3);

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 1);
        Assert.assertArrayEquals(new int[]{1,2,3}, page1.stream().mapToInt(SimpleDataRepository.Product::getId).toArray());
        Assert.assertArrayEquals(new int[]{4,5,6}, page2.stream().mapToInt(SimpleDataRepository.Product::getId).toArray());
        Assert.assertArrayEquals(new int[]{}, page5.stream().mapToInt(SimpleDataRepository.Product::getId).toArray());
    }

    /***
     * Checks if cache is implemented correctly for updating product
     * @difficulty 2
     */
    @org.junit.Test(timeout = 5800)
    public void updateProductInRepository() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();
        int randomProductId = (int)Math.floor(Math.random() * 6) + 2;
        // Inital get data
        SimpleDataRepository.Product product1 = dataRepository.getProductsById(1);
        Collection<SimpleDataRepository.Product> productData = dataRepository.getAllProducts();

        // Get data again
        product1 = dataRepository.getProductsById(1);
        productData = dataRepository.getAllProducts();

        // Update product
        dataRepository.updateProduct(1, new SimpleDataRepository.Product("Updated!",99.99, false));


        // Get data, again... we should request new ones since they were changed...
        product1 = dataRepository.getProductsById(1);
        productData = dataRepository.getAllProducts();

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 5);
        Assert.assertNotNull(product1);
        Assert.assertEquals(1, product1.getId());
        Assert.assertEquals("Updated!", product1.getName());
        Assert.assertEquals(productData.size(), 9);
    }

    /***
     * Checks if cache is implemented correctly for different element types
     * @difficulty 3
     */
    @org.junit.Test(timeout = 2800)
    public void getAllProductsAndWarehouses() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();
        // Inital get data
        Collection<SimpleDataRepository.Product> productData = dataRepository.getAllProducts();
        Collection<SimpleDataRepository.Warehouse> warehouseData = dataRepository.getAllWarehouses();

        // Get data again
        productData = dataRepository.getAllProducts();
        warehouseData = dataRepository.getAllWarehouses();

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 2);
        Assert.assertEquals(productData.size(), 9);
        Assert.assertEquals(warehouseData.size(), 3);
        Assert.assertArrayEquals(new int[]{7,8,9}, warehouseData.stream().findFirst().get().getProducts().stream().mapToInt(SimpleDataRepository.Product::getId).toArray());
    }


    /***
     * Checks if cache is implemented correctly for upsert (update or insert) product - insert
     * @difficulty 3
     */
    @org.junit.Test(timeout = 6800)
    public void upsertInsertProductInRepository() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();
        int randomProductId = (int)Math.floor(Math.random() * 6) + 2;
        // Inital get data
        SimpleDataRepository.Product product1 = dataRepository.getProductsById(1);
        Collection<SimpleDataRepository.Product> productData = dataRepository.getAllProducts();
        Collection<SimpleDataRepository.Warehouse> warehouseData = dataRepository.getAllWarehouses();

        // Get data again
        product1 = dataRepository.getProductsById(1);
        productData = dataRepository.getAllProducts();

        // Insert product
        dataRepository.upsertProduct(new SimpleDataRepository.Product("Updated!",99.99, false));


        // Get data, again...
        product1 = dataRepository.getProductsById(1); // This should not be requested again
        SimpleDataRepository.Product product10 = dataRepository.getProductsById(10); // This should be requested
        productData = dataRepository.getAllProducts(); // This should be requested
        warehouseData = dataRepository.getAllWarehouses(); // This should not be requested

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 6);
        Assert.assertNotNull(product1);
        Assert.assertEquals(1, product1.getId());
        Assert.assertEquals("Updated!", product10.getName());
        Assert.assertEquals(productData.size(), 10);
    }

    /***
     * Checks if cache is implemented correctly for upsert (update or insert) product - update
     * @difficulty 3
     */
    @org.junit.Test(timeout = 7800)
    public void upsertUpdateProductInRepository() throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        CachingAndPagingDataRepository dataRepository = new CachingAndPagingDataRepository();
        int randomProductId = (int)Math.floor(Math.random() * 6) + 2;
        // Inital get data
        SimpleDataRepository.Product product1 = dataRepository.getProductsById(1);
        Collection<SimpleDataRepository.Product> productData = dataRepository.getAllProducts();
        Collection<SimpleDataRepository.Warehouse> warehouseData = dataRepository.getAllWarehouses();

        // Get data again
        product1 = dataRepository.getProductsById(1);
        productData = dataRepository.getAllProducts();

        // Update product
        product1.setName("Updated!");
        dataRepository.upsertProduct(product1);


        // Get data, again...
        product1 = dataRepository.getProductsById(1); // This should be requested
        productData = dataRepository.getAllProducts(); // This should be requested
        warehouseData = dataRepository.getAllWarehouses(); // This should be requested

        // Checks
        Assert.assertEquals(dataRepository.getRepositoryCalls(), 7);
        Assert.assertNotNull(product1);
        Assert.assertEquals(1, product1.getId());
        Assert.assertEquals("Updated!", product1.getName());
        Assert.assertEquals(productData.size(), 9);
    }
}
