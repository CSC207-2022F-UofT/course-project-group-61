package storemainmenu;

public class StoreMainMenuRequestModel {

    private buttonOption option;

    public StoreMainMenuRequestModel(buttonOption option) {
        this.option = option;
    }

    public buttonOption getOption() {
        return this.option;
    }
}
