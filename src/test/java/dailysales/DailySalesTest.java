package dailysales;

import adminmainmenu.AdminMainMenuViewModel;
import database.FacilityDbGateway;
import database.ProductDbGateway;
import database.UserDbGateway;
import entities.Facility;
import entities.FacilityType;
import entities.FacilityUser;
import newuser.NewUserController;
import newuser.NewUserInteractor;
import newuser.NewUserPresenter;
import newuser.NewUserViewModel;
import org.junit.Before;
import org.junit.Test;
import storemainmenu.StoreMainMenuViewModel;
import userlogin.UserLoginController;
import userlogin.UserLoginInteractor;
import userlogin.UserLoginPresenter;
import userlogin.UserLoginViewModel;
import warehousemainmenu.WarehouseMainMenuViewModel;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class DailySalesTest {

    private final FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
    private final ProductDbGateway productDbGateway = new ProductDbGateway();
    private final UserDbGateway userDbGateway = new UserDbGateway();
    private final DailySalesController dailySalesController = new DailySalesController(new DailySalesInteractor(new DailySalesPresenter(new DailySalesViewModel(), new StoreMainMenuViewModel()), facilityDbGateway, productDbGateway));
    private final Facility facility = new Facility("Test Store", FacilityType.STORE);
    private final NewUserController newUserController = new NewUserController(new NewUserInteractor(new NewUserPresenter(new NewUserViewModel(), new AdminMainMenuViewModel()), userDbGateway));
    private final UserLoginController userLoginController = new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(new UserLoginViewModel(), new StoreMainMenuViewModel(), new WarehouseMainMenuViewModel(), new AdminMainMenuViewModel()), userDbGateway));




    @Before
    public void setup() {
        this.facilityDbGateway.fileReset();
        this.facility.addProduct(1L, 50);
        this.facilityDbGateway.updateFacility(this.facility);
    }

    @Test
    public void testSellOneProduct() {
        userDbGateway.fileReset();
        UUID uuid = facility.getFacilityID();
        FacilityUser user = new FacilityUser("Store User", "Password", uuid, FacilityType.STORE);
        newUserController.createStoreUser(user.getUsername(), user.getPassword(), user.getFacilityID());
        userLoginController.login("Store User", "Password");

        HashMap<Long, Integer> oneProdHash = new HashMap<Long, Integer>();
        oneProdHash.put(1L, 4);
        this.dailySalesController.inputDailySales(oneProdHash);

        assertEquals(46, (int) this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(1L));
    }

    @Test
    public void testSellMultiProduct() {
        userDbGateway.fileReset();
        UUID uuid = facility.getFacilityID();
        FacilityUser user = new FacilityUser("Store User", "Password", uuid, FacilityType.STORE);
        newUserController.createStoreUser(user.getUsername(), user.getPassword(), user.getFacilityID());
        userLoginController.login("Store User", "Password");


        HashMap<Long, Integer> multiProdHash = new HashMap<Long, Integer>();
        multiProdHash.put(1L, 4);
        multiProdHash.put(2L, 7);
        this.facility.addProduct(2L, 20);
        this.facilityDbGateway.updateFacility(this.facility);
        this.dailySalesController.inputDailySales(multiProdHash);

        assertEquals(46, (int) this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(1L));
        assertEquals(13, (int) this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(2L));
    }

}
