package dailysales;

import database.FacilityDbGateway;
import entities.Facility;
import entities.FacilityType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DailySalesTest {

    private final DailySalesController dailySalesController = new DailySalesController();
    private final FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
    private Facility facility = new Facility("Test Store", FacilityType.STORE);


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

        assertEquals(46, (int) this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(1L));
    }

    @Test
    public void testSellMultiProduct() {
        HashMap<Long, Integer> multiProdHash = new HashMap<Long, Integer>();
        multiProdHash.put(1L, 4);
        multiProdHash.put(2L, 7);
        this.facility.addProduct(2L, 20);
        this.facilityDbGateway.updateFacility(this.facility);
        this.dailySalesController.updateDailySales(this.facility.getFacilityID(), multiProdHash);

        assertEquals(46, (int) this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(1L));
        assertEquals(13, (int) this.facilityDbGateway.getFacility(this.facility.getFacilityID()).getUPCQuantity(2L));
    }

}
