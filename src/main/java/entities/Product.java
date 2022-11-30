package entities;

import java.io.Serializable;

/* Product holds information about an item such as name (ex. Apple), UPC (ex. 123456789123), and price (ex. 5)*/
public class Product implements Serializable {

    private String name;
    private long UPC;
    private int price;

    public Product(String name, Long UPC, int price){
        this.name = name;
        this.UPC = UPC;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public long getUPC(){
        return UPC;
    }

    public void setUPC(Long newUPC){
        this.UPC = newUPC;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int newPrice){
        this.price = newPrice;
    }
}

