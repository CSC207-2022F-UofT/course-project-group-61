package itemlookup;

import database.ProductDb;
import database.ProductDbGateway;
import entities.Product;

import java.util.*;

public class ItemLookupInteractor implements ItemLookupInputBoundary{

    ProductDbGateway readWriter;

    ProductDb productDb;
    ItemLookupOutputBoundary presenter;

    public ItemLookupInteractor(ItemLookupOutputBoundary presenter, ProductDb productDb) {
        this.presenter = presenter;
        this.productDb = productDb;
    }

    //if product not found, will pass null up through the return stack and presenter will handle the output
    //TODO: what to return if upc doesn't exist
    public ItemLookupResponseModel lookupByUPC(ItemLookupRequestModel request) {
        Product product = productDb.getProduct(request.getUPC());
        ItemLookupResponseModel response;
        if (product == null) {
            response = new ItemLookupResponseModel(null, FailReason.INVALID_UPC);
            presenter.prepareFailView(response);
        } else {
            List<Object> responseList = new ArrayList<>();
            responseList.add(product.getName());
            responseList.add(product.getUPC());
            responseList.add(product.getPrice());
            response = new ItemLookupResponseModel(responseList, null);
            presenter.prepareSuccessView(response);
        }
        //HashMap<Long, Product> products = this.readWriter.getAllProducts();
        return response;
    }

    //TODO: what to return if name doesn't exist
    public ItemLookupResponseModel lookupByName(ItemLookupRequestModel request) {
        HashMap<Long, Product> products = productDb.getAllProducts();
        ItemLookupResponseModel response;
        if (products.size() == 0) {
            response = new ItemLookupResponseModel(null, FailReason.INVALID_NAME);
            presenter.prepareFailView(response);
        } else {
            List<Object> returnList = new ArrayList<>();
            for(Map.Entry<Long, Product> entry : products.entrySet()) {
                if (Objects.equals(request.getName(), entry.getValue().getName())) {
                    returnList.add(entry.getValue().getName());
                    returnList.add(entry.getValue().getUPC());
                    returnList.add(entry.getValue().getPrice());
                }
            }
            response = new ItemLookupResponseModel(returnList, null);
            presenter.prepareSuccessView(response);
        }
        return response;
    }
}
