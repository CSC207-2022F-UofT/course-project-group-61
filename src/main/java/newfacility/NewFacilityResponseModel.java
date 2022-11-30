package newfacility;

import java.util.List;

public class NewFacilityResponseModel {

    private final List<Object> infoList;

    private final newfacility.FailReason failReason;

    public NewFacilityResponseModel(List<Object> infoList, newfacility.FailReason failReason) {
        this.infoList = infoList;
        this.failReason = failReason;
    }

    public List<Object> getInfoList() {
        return infoList;
    }

    public FailReason getFailReason() {return failReason;}
}
