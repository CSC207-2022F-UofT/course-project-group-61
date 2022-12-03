package newitem;


public class NewItemController {

    private final NewItemInputBoundary inputBoundary;


    public NewItemController(NewItemInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public NewItemResponseModel newItem(String name, String sUPC, String sPrice) {

        try {
            if (sUPC.length() != 12) {
                throw new Exception();
            }
            long upc = Long.parseLong(sUPC);
            int price = Integer.parseInt(sPrice);
            if (price < 0) {
                throw new Exception();
            }
            NewItemRequestModel request = new NewItemRequestModel(name, upc, price);
            return inputBoundary.newItem(request);

        } catch (Exception e) {

            return new NewItemResponseModel(null, NewItemStatus.INVALID_INPUT);

        }
    }
}
