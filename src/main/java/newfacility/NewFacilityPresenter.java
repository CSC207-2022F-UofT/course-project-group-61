package newfacility;

import adminmainmenu.AdminMainMenuViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewFacilityPresenter implements NewFacilityOutputBoundary {

    private NewFacilityViewModel viewModel;

    public NewFacilityPresenter(NewFacilityViewModel viewModel, AdminMainMenuViewModel adminMainMenuViewModel) {
        this.viewModel = viewModel;
        this.adminMainMenuViewModel = adminMainMenuViewModel;
    }
    private AdminMainMenuViewModel adminMainMenuViewModel;

    @Override
    public void prepareSuccessView(NewFacilityResponseModel model) {
        List<Object> infoList = model.getInfoList();
        List<String> stringList = new ArrayList<>();
        for (Object o : infoList) {
            stringList.add(o.toString());
        }
        viewModel.viewInfo(stringList);
        //TODO: Store user data somewhere
    }

    @Override
    public void prepareFailView(NewFacilityResponseModel model) {
        viewModel.failed(model.getFailReason());
    }

    @Override
    public void returnToMainMenu() {
        viewModel.setVisible(false);
        adminMainMenuViewModel.setVisible(true);
    }
}
