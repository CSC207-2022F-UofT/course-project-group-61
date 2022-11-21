package itemlookup;

import adminmainmenu.AdminMainMenuViewModel;
import entities.AdminUser;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;
import storemainmenu.StoreMainMenuViewModel;
import warehousemainmenu.WarehouseMainMenuViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemLookupPresenter implements ItemLookupOutputBoundary {

    private ItemLookupViewModel viewModel;

    public ItemLookupPresenter(ItemLookupViewModel viewModel) {
        this.viewModel = viewModel;
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
}
