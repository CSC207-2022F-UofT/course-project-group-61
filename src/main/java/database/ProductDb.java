package database;

import entities.Product;

import java.util.HashMap;

public interface ProductDb {

    HashMap<Long, Product> getAllProducts();

    Product getProduct(Long upc);

    boolean updateProduct(Product product);
}
