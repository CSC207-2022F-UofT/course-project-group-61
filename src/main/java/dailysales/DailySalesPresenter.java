package dailysales;

import storemainmenu.StoreMainMenuViewModel;

public class DailySalesPresenter implements DailySalesOutputBoundary {

    private final DailySalesViewModel viewModel;
    private final StoreMainMenuViewModel storeMainMenuViewModel;

    public DailySalesPresenter(DailySalesViewModel viewModel, StoreMainMenuViewModel storeMainMenuViewModel) {
        this.viewModel = viewModel;
        this.storeMainMenuViewModel = storeMainMenuViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewModel.completed();
        viewModel.setVisible(false);
        storeMainMenuViewModel.setVisible(true);
    }

    @Override
    public void returnToMenu() {
        viewModel.setVisible(false);
        storeMainMenuViewModel.setVisible(true);
    }
}
