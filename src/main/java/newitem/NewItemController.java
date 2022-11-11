package newitem;

public class NewItemController {
    private final NewItemInteractor newItemInteractor;

    public NewItemController() {
        this.newItemInteractor = new NewItemInteractor();
    }

    public boolean addNewItem(String name, Long upc, int price) {
        return this.newItemInteractor.addNewItem(name, upc, price);
    }
}
