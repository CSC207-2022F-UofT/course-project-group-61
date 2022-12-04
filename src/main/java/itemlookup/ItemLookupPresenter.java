package itemlookup;

import storemainmenu.StoreMainMenuViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemLookupPresenter implements ItemLookupOutputBoundary {

    private final ItemLookupViewModel viewModel;
    private final StoreMainMenuViewModel storeMainMenuViewModel;

    public ItemLookupPresenter(ItemLookupViewModel viewModel, StoreMainMenuViewModel storeMainMenuViewModel) {
        this.viewModel = viewModel;
        this.storeMainMenuViewModel = storeMainMenuViewModel;
    }

    @Override
    public void prepareSuccessView(ItemLookupResponseModel model) {
        List<Object> infoList = model.getInfoList();
        List<String> stringList = new ArrayList<>();
        for (Object o : infoList) {
            stringList.add(o.toString());
        }
        viewModel.viewInfo(stringList);
        //TODO: Store user data somewhere
    }

    @Override
    public void prepareFailView(ItemLookupResponseModel model) {
        viewModel.failed(model.getFailReason());
    }

    @Override
    public void returnToMenu() {
        viewModel.setVisible(false);
        storeMainMenuViewModel.setVisible(true);
    }
}
