package newfacility;

import java.util.ArrayList;
import java.util.List;

public class NewFacilityPresenter implements NewFacilityOutputBoundary {

    private NewFacilityViewModel viewModel;

    public NewFacilityPresenter(NewFacilityViewModel viewModel) {
        this.viewModel = viewModel;
    }

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
}
