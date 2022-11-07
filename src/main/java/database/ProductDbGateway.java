package database;

import entities.Order;
import entities.Product;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class ProductDbGateway implements ProductDb {

    private final String PRODUCT_FILE_PATH = "data/products.ser";
    private final DBReadWriter db;

    public ProductDbGateway() {
        this.db = new DBReadWriter(PRODUCT_FILE_PATH);
    }

    @Override
    public HashMap<Long, Product> getAllProducts() {
        try {
            return (HashMap<Long, Product>) db.read();
        } catch (EOFException eof) {
            HashMap<Long, Product> tempMap = new HashMap<Long, Product>();
            try {
                this.db.write(tempMap);
                return (HashMap<Long, Product>) db.read();
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            HashMap<Long, Product> products = getAllProducts();
            products.put(product.getUPC(), product);
            db.write(products);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getProduct(Long upc) {
        try {
            HashMap<Long, Product> products = getAllProducts();
            return products.get(upc);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
