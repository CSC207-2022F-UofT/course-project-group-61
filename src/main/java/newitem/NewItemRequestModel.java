package newitem;

public class NewItemRequestModel {

    private final String name;
    private final long UPC;
    private final int price;

    public NewItemRequestModel(String name, long UPC, int price) {
        this.name = name;
        this.UPC = UPC;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public long getUPC() {
        return this.UPC;
    }

    public int getPrice() {
        return this.price;
    }

}
