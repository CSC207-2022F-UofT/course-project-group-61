package entities;

public class Product {

    private String name;
    private long UPC;
    private int price;

    public Product(String name, int UPC, int price){
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

    public void setUPC(int newUPC){
        this.UPC = newUPC;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int newPrice){
        this.price = newPrice;
    }
}