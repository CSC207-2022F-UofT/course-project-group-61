package newitem;

import entities.Product;

public class NewItemController {

    private final NewItemInteractor newItemInteractor;

    public NewItemController() {
        this.newItemInteractor = new NewItemInteractor();
    }

    //returns Product for testing purposes but maybe that's what the user will be given upon execution of the request
    public Product newItem(String name, long UPC, int price) {
        return this.newItemInteractor.addNewItem(name, UPC, price);
        //TODO: return logic for presenter
    }
}
