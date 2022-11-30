package dailysales;

import database.FacilityDbGateway;
import entities.Facility;
import entities.FacilityType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class DailySalesTest {

    private final FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
    private final DailySalesInteractor dailySalesInteractor = new DailySalesInteractor(facilityDbGateway);
    private final DailySalesController dailySalesController = new DailySalesController(dailySalesInteractor);
    private final Facility facility = new Facility("Test Store", FacilityType.STORE);


    @Before
    public void setup() {
        this.facilityDbGateway.fileReset();
        this.facility.addProduct(1L, 50);
        this.facilityDbGateway.updateFacility(this.facility);
    }

    @Test
    public void testSellOneProduct() {
        HashMap<Long, Integer> oneProdHash = new HashMap<Long, Integer>();
        oneProdHash.put(1L, 4);
        this.dailySalesController.inputDailySales(oneProdHash);

        assertEquals(this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(1L), 46);
    }

    @Test
    public void testSellMultiProduct() {
        HashMap<Long, Integer> multiProdHash = new HashMap<Long, Integer>();
        multiProdHash.put(1L, 4);
        multiProdHash.put(2L, 7);
        this.facility.addProduct(2L, 20);
        this.facilityDbGateway.updateFacility(this.facility);
        this.dailySalesController.inputDailySales(multiProdHash);

        assertEquals(this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(1L), 46);
        assertEquals(this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(2L), 13);
    }

}
