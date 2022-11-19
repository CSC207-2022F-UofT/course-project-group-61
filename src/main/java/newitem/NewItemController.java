package newitem;

public class NewItemController {

    private final NewItemInteractor newItemInteractor;

    public NewItemController() {
        this.newItemInteractor = new NewItemInteractor();
    }

    //returns the item UPC mostly for testing purposes but also maybe that's what the user will be given upon
    //execution of the request
    public long newItem(String name, long UPC, int price) {
        return this.newItemInteractor.addNewItem(name, UPC, price);
        //TODO: return logic for presenter
    }
}
