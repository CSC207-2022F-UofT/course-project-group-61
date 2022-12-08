package newuser;

public interface NewUserOutputBoundary {

    void prepareSuccessView(NewUserResponseModel model);

    void prepareFailView(NewUserResponseModel model);

    void returnToMenu();
}
