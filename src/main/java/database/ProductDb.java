package database;

import entities.Product;

import java.util.HashMap;

public interface ProductDb {

    HashMap<Integer, Product> getAllProducts();

    Product getProduct(Integer upc);

    boolean updateProduct(Product product);
}
