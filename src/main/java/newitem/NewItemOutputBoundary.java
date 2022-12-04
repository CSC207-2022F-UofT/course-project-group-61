package newitem;


public interface NewItemOutputBoundary {

    void prepareSuccessView(NewItemResponseModel model);

    void prepareFailView(NewItemResponseModel model);

}