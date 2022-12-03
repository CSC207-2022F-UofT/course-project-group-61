package newitem;

public class NewItemPresenter implements NewItemOutputBoundary {


    private NewItemViewModel viewModel;

    public NewItemPresenter(NewItemViewModel viewModel) {

        this.viewModel = viewModel;

    }

    @Override
    public void prepareSuccessView(NewItemResponseModel model) {
        viewModel.changeStatus(model.getStatus());
    }

    @Override
    public void prepareFailView(NewItemResponseModel model) {
        viewModel.changeStatus(model.getStatus());
    }
}