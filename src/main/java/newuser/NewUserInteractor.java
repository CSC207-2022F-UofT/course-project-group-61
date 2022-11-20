package newuser;

import database.UserDb;
import entities.FacilityUser;
import entities.User;
import entities.FacilityType;

import java.util.UUID;


public class NewUserInteractor implements NewUserInputBoundary{

    private final UserDb readWriter;
    private final NewUserOutputBoundary presenter;
    public NewUserInteractor(NewUserOutputBoundary presenter, UserDb userDb){
        this.readWriter = userDb;
        this.presenter = presenter;
    }

    @Override
    public NewUserResponseModel addNewUser(NewUserRequestModel request){

        User newUser = new FacilityUser(request.getUsername(), request.getPassword(),
                request.getFacilityID(), request.getType());

        NewUserResponseModel response;

        if (readWriter.getUser(request.getUsername()) != null){
            response = new NewUserResponseModel(null, NewUserStatus.USERNAME_EXISTS);
            presenter.prepareFailView(response);
            return response;
        }
        else if (request.getPassword().length() < 5){
            response = new NewUserResponseModel(null, NewUserStatus.PASSWORD_TOO_SHORT);
            presenter.prepareFailView(response);
            return response;
        }
        else {
            readWriter.updateUser(newUser);
            response = new NewUserResponseModel(newUser, NewUserStatus.SUCCESS);
            presenter.prepareSuccessView(response);
            return response;
        }
    }
}
