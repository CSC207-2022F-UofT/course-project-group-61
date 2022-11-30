package itemlookup;

import java.util.ArrayList;
import java.util.List;

public class ItemLookupPresenter implements ItemLookupOutputBoundary {

    private final ItemLookupViewModel viewModel;

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
