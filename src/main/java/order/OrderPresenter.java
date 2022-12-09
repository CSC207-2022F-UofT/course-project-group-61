package order;

import storemainmenu.StoreMainMenuViewModel;

public class OrderPresenter implements OrderOutputBoundary {

    private final OrderViewModel viewModel;
    private final StoreMainMenuViewModel storeViewModel;

    public OrderPresenter(OrderViewModel viewModel, StoreMainMenuViewModel storeViewModel) {
        this.viewModel = viewModel;
        this.storeViewModel = storeViewModel;
    }

    @Override
    public void prepareSuccessView(OrderResponseModel response) {
        viewModel.orderCompleted();
        viewModel.setVisible(false);
        storeViewModel.setVisible(true);
    }

    @Override
    public void returnToMenu() {
        viewModel.setVisible(false);
        storeViewModel.setVisible(true);
    }
}
