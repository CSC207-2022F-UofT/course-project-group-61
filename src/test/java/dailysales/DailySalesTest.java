package dailysales;

import database.FacilityDbGateway;
import entities.Facility;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class DailySalesTest {

    private final DailySalesController dailySalesController = new DailySalesController();
    private final FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
    private Facility facility = new Facility("Test Store", "Store");


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
        this.dailySalesController.updateDailySales(this.facility.getFacilityID(), oneProdHash);

        assertEquals(this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(1L), 46);
    }

    @Test
    public void testSellMultiProduct() {
        HashMap<Long, Integer> multiProdHash = new HashMap<Long, Integer>();
        multiProdHash.put(1L, 4);
        multiProdHash.put(2L, 7);
        this.facility.addProduct(2L, 20);
        this.facilityDbGateway.updateFacility(this.facility);
        this.dailySalesController.updateDailySales(this.facility.getFacilityID(), multiProdHash);

        assertEquals(this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(1L), 46);
        assertEquals(this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(2L), 13);
    }

}