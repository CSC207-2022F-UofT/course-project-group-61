package itemlookup;

import database.FacilityDb;
import database.ProductDb;
import database.ProductDbGateway;
import entities.Facility;
import entities.Product;

import java.util.*;

public class ItemLookupInteractor implements ItemLookupInputBoundary{

    ProductDbGateway readWriter;

    ProductDb productDb;
    FacilityDb facilityDb;
    ItemLookupOutputBoundary presenter;

    public ItemLookupInteractor(ItemLookupOutputBoundary presenter, ProductDb productDb, FacilityDb facilityDb) {
        this.presenter = presenter;
        this.productDb = productDb;
        this.facilityDb = facilityDb;
    }

    //if product not found, will pass null up through the return stack and presenter will handle the output
    //TODO: what to return if upc doesn't exist
    public ItemLookupResponseModel lookupByUPC(ItemLookupRequestModel request) {
        Product product = productDb.getProduct(request.getUPC());
        HashMap<UUID, Facility> facilities = facilityDb.getAllFacilities();
        ItemLookupResponseModel response;
        if (product == null) {
            response = new ItemLookupResponseModel(null, FailReason.INVALID_UPC);
            presenter.prepareFailView(response);
        } else {
            List<Object> responseList = new ArrayList<>();
            responseList.add(product.getName());
            responseList.add(product.getUPC());
            responseList.add(product.getPrice());
            //HashMap<UUID, Integer> facilityQuantities = new HashMap<>();
            for (Facility facility : facilities.values()) {
                if (!Objects.isNull(facility.getUPCQuantity(request.getUPC()))) {
                    responseList.add(facility.getFacilityID());
                    responseList.add(facility.getUPCQuantity(request.getUPC()));

                    //facilityQuantities.put(facility.getFacilityID(), facility.getUPCQuantity(request.getUPC()));
                }
            }
            //responseList.add(facilityQuantities);

            response = new ItemLookupResponseModel(responseList, null);
            presenter.prepareSuccessView(response);
        }
        return response;
    }

    //TODO: what to return if name doesn't exist
    public ItemLookupResponseModel lookupByName(ItemLookupRequestModel request) {
        HashMap<Long, Product> products = productDb.getAllProducts();
        HashMap<UUID, Facility> facilities = facilityDb.getAllFacilities();
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
            //HashMap<UUID, Integer> facilityQuantities = new HashMap<>();
            for (Facility facility : facilities.values()) {
                if (!Objects.isNull(facility.getUPCQuantity((Long) returnList.get(1)))) {
                    returnList.add(facility.getFacilityID());
                    returnList.add(facility.getUPCQuantity((Long) returnList.get(1)));

                    //facilityQuantities.put(facility.getFacilityID(), facility.getUPCQuantity((Long) returnList.get(1)));
                }
            }
            //returnList.add(facilityQuantities);

            response = new ItemLookupResponseModel(returnList, null);
            presenter.prepareSuccessView(response);
        }
        return response;
    }
}
