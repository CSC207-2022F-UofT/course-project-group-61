package storemainmenu;

public class StoreMainMenuResponseModel {

    private buttonOption option;

    public StoreMainMenuResponseModel(buttonOption option) {
        this.option = option;
    }

    public buttonOption getOption() {
        return this.option;
    }
}
