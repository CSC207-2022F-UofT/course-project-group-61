package itemlookup;

/* things to test:
    CURRENTLY ONLY IMPLEMENTED FOR NON-EMPTY DATABASE WITH VALID LOOKUP INPUTS
    - lookup by upc
    -lookup by name
     */

import adminmainmenu.AdminMainMenuViewModel;
import database.FacilityDbGateway;
import database.ProductDbGateway;
import entities.Product;
import org.junit.Before;
import org.junit.Test;
import storemainmenu.StoreMainMenuViewModel;
import warehousemainmenu.WarehouseMainMenuViewModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ItemLookupTest {

    private ItemLookupController itemLookupController;
    private List<Object> testList;

    @Before
    public void setup() {
        ProductDbGateway productDbGateway = new ProductDbGateway();
        productDbGateway.fileReset();
        Product testProduct = new Product("Apple", 123456789123L, 5);
        productDbGateway.updateProduct(testProduct);
        FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
        facilityDbGateway.fileReset();
        this.itemLookupController = new ItemLookupController(new ItemLookupInteractor(new ItemLookupPresenter(new ItemLookupViewModel(), new StoreMainMenuViewModel(), new WarehouseMainMenuViewModel(), new AdminMainMenuViewModel()), productDbGateway, facilityDbGateway));
        this.testList = Arrays.asList("Apple", 123456789123L, 5);
    }

    @Test
    public void testLookupByUPC() {
        assertEquals(this.itemLookupController.lookupByUPC(123456789123L).getInfoList(), testList);
        //assertTrue(checkAttributes(this.itemLookupController.lookupByUPC(123456789123L), "Apple", 123456789123L, 5));
        assertNull(this.itemLookupController.lookupByUPC(111111111111L).getInfoList());
    }

    @Test
    public void testLookupByName() {
        assertEquals(this.itemLookupController.lookupByName("Apple").getInfoList(), testList);
        //assertTrue(checkAttributes(this.itemLookupController.lookupByName("Apple"), "Apple", 123456789123L, 5));
        assertNull(this.itemLookupController.lookupByName("Orange").getInfoList());
    }

}
