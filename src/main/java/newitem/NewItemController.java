package newitem;


import exceptions.NewItemException;

public class NewItemController {

    private final NewItemInputBoundary inputBoundary;


    public NewItemController(NewItemInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public NewItemResponseModel newItem(String name, String sUPC, String sPrice) {

        try {
            if (sUPC.length() != 12) {
                throw new NewItemException();
            }
            long upc = Long.parseLong(sUPC);
            int price = Integer.parseInt(sPrice);
            if (price < 0) {
                throw new NewItemException();
            }
            NewItemRequestModel request = new NewItemRequestModel(name, upc, price);
            return inputBoundary.newItem(request);

        } catch (NumberFormatException | NewItemException exception) {

            return new NewItemResponseModel(null, NewItemStatus.INVALID_INPUT);

        }
    }

    public void returnToMainMenu() {
        inputBoundary.returnToMainMenu();
    }
}
