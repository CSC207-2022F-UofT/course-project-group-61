package database;

import entities.Product;

import java.util.HashMap;

public class ProductDbGateway implements ProductDb {

    private String PRODUCT_FILE_PATH = "data/orders.ser";
    private DBReadWriter db;

    public ProductDbGateway() {
        this.db = new DBReadWriter(PRODUCT_FILE_PATH);
    }

    @Override
    public HashMap<Integer, Product> getAllProducts() {
        try {
            return (HashMap<Integer, Product>) db.read();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            HashMap<Integer, Product> products = getAllProducts();
//            products.put(product.getu(), product);
            db.write(products);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getProduct(Integer upc) {
        try {
            HashMap<Integer, Product> products = getAllProducts();
            return products.get(upc);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
