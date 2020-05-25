package mini.java.basic.collections.test.solution;

import java.io.IOException;
import java.util.*;

public class SimpleDataRepository {
    private Map<Integer, Product> products;
    private Map<Integer, Warehouse> warehouses;
    private DataLoader dataLoader = new DataLoader();

    private int calls = 0;
    private int productLastIndex = 9;
    private int warehouseLastIndex = 3;

    /**
     * Repository initializaion using external datasource
     */
    public SimpleDataRepository(String dataFileName) throws IOException, NoDataException, WarehouseDataMalformedException, ProductDataMalformedException {
        products = new HashMap<>();
        warehouses = new HashMap<>();
        for (Object[] objects1 : dataLoader.getData(dataFileName, "products")) {
            Product p = DataMappers.mapToProduct(objects1);
            products.put(p.id, p);
        }
        for (Object[] objects : dataLoader.getData(dataFileName, "warehouses")) {
            Warehouse w = DataMappers.mapToWarehouse(objects);
            warehouses.put(w.id, w);
        }
    }

    /**
     * Return all products
     * @return List with all products in database
     */
    public Collection<Product> getProducts() {
        fakeProcess();
        return products.values();
    }

    /**
     * Returns product by id.
     * @param id Product id
     * @return Product data
     */
    public Product getProductById(int id) {
        fakeProcess();
        return products.get(id);
    }

    /**
     * Return all warehouses
     * @return List with all warehouses in database
     */
    public Collection<Warehouse> getWarehouses() {
        fakeProcess();
        return warehouses.values();
    }

    /**
     * Returns warehouse by id. Not used, but for the sake of completeness
     * @param id Warehouse id
     * @return Warehouse data
     */
    public Warehouse getWarehouseById(int id) {
        fakeProcess();
        return warehouses.get(id);
    }

    /**
     * Upserts product.
     * @param product Product data
     */
    public boolean upsertProduct(Product product) {
        fakeProcess();
        boolean updated = true;
        if (!products.containsKey(product.id)) {
            product.id = ++productLastIndex;
            updated = false;
        }
        products.put(product.id, product);
        return updated;
    }

    /**
     * Updates product.
     * @param id Id of product to update
     * @param product Product data
     */
    public void updateProduct(Integer id, Product product) {
        fakeProcess();
        if (!products.containsKey(id)) throw new RepositoryElementNotFoundException("Element not found in database, contents not changed");
        product.id = id;
        products.put(id, product);
    }

    /**
     * Upserts warehouse. Not used, but for the sake of completeness
     * @param warehouse Warehouse data
     */
    public boolean upsertWarehouse(Warehouse warehouse) {
        fakeProcess();
        boolean updated = true;
        if (!warehouses.containsKey(warehouse.id)) {
            warehouse.id = ++warehouseLastIndex;
            updated = false;
        }
        warehouses.put(warehouse.id, warehouse);
        return updated;
    }

    /**
     * Updates warehouse. Not used, but for the sake of completeness
     * @param id Id of warehouse to update
     * @param warehouse Warehouse data
     */
    public void updateWarehouse(Integer id, Warehouse warehouse) {
        fakeProcess();
        if (!warehouses.containsKey(id)) throw new RepositoryElementNotFoundException("Element not found in database, contents not changed");
        warehouse.id = id;
        warehouses.put(id, warehouse);
    }

    /**
     * Fake processing timeout, for testing
     */
    private void fakeProcess() {
        try {
            calls++;
            Thread.currentThread().sleep(0);
        } catch (InterruptedException e) {
            // Sleep interrupted
        }
    }

    public int getCalls() {
        return calls;
    }

    /***
     * Product entity
     */
    public static class Product {
        private int id;
        private String name;
        private double price;
        private boolean expires;

        public Product(int id, String name, double price, boolean expires) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.expires = expires;
        }

        public Product(String name, double price, boolean expires) {
            this.id = -1;
            this.name = name;
            this.price = price;
            this.expires = expires;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public boolean isExpires() {
            return expires;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setExpires(boolean expires) {
            this.expires = expires;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return id == product.id &&
                    Double.compare(product.price, price) == 0 &&
                    expires == product.expires &&
                    Objects.equals(name, product.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(id, name, price, expires);
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    ", expires=" + expires +
                    '}';
        }
    }

    /**
     * Warehouse entity
     */
    public static class Warehouse {
        private int id;
        private final String location;
        private final boolean open;
        private final List<Integer> productIds;

        /**
         * Products must be initialized after loading from database using data
         * from productIds field, they are never stored in database
         */
        transient private List<Product> products;

        public Warehouse(int id, String location, boolean open, List<Integer> products) {

            this.id = id;
            this.location = location;
            this.open = open;
            this.productIds = products;
        }

        public int getId() {
            return id;
        }

        public String getLocation() {
            return location;
        }

        public boolean isOpen() {
            return open;
        }

        public List<Integer> getProductIds() {
            return productIds;
        }


        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Warehouse warehouse = (Warehouse) o;
            return id == warehouse.id &&
                    open == warehouse.open &&
                    Objects.equals(location, warehouse.location) &&
                    Objects.equals(productIds, warehouse.productIds);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, location, open, productIds);
        }

        @Override
        public String toString() {
            return "Warehouse{" +
                    "id=" + id +
                    ", location='" + location + '\'' +
                    ", open=" + open +
                    ", products=" + productIds +
                    '}';
        }

    }

    private class RepositoryElementNotFoundException extends RuntimeException {
        private String message;

        public RepositoryElementNotFoundException(String s) {
            message = s;
        }
    }
}
