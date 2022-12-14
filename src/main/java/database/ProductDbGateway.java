package database;

import entities.Product;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;

public class ProductDbGateway implements ProductDb {

    private final DBReadWriter db;

    public ProductDbGateway() {
        String PRODUCT_FILE_PATH = "data/products.ser";
        this.db = new DBReadWriter(PRODUCT_FILE_PATH);
    }

    @Override
    public HashMap<Long, Product> getAllProducts() {
        try {
            return (HashMap<Long, Product>) db.read();
        } catch (EOFException eof) {
            HashMap<Long, Product> tempMap = new HashMap<>();
            try {
                this.db.write(tempMap);
                return (HashMap<Long, Product>) db.read();
            } catch(IOException | ClassNotFoundException e) {
                return null;
            }
        } catch (Exception e) {
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
            return false;
        }
    }

    @Override
    public Product getProduct(Long upc) {
        try {
            HashMap<Long, Product> products = getAllProducts();
            return products.get(upc);
        } catch(Exception e) {
            return null;
        }
    }

    //for testing purposes
    public void fileReset() {
        try {
            HashMap<Long, Product> newHash = new HashMap<>();
            db.write(newHash);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
