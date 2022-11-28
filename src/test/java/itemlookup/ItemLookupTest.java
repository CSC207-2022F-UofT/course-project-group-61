package itemlookup;

/* things to test:
    CURRENTLY ONLY IMPLEMENTED FOR NON-EMPTY DATABASE WITH VALID LOOKUP INPUTS
    - lookup by upc
    -lookup by name
     */

import database.ProductDbGateway;
import entities.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ItemLookupTest {

    private ItemLookupController itemLookupController;
    private ProductDbGateway productDbGateway;
    private Product testProduct;

    public boolean checkAttributes(Product product, String expName, Long expUPC, int expPrice) {
        return Objects.equals(product.getName(), expName) & Objects.equals(product.getUPC(), expUPC) &
                Objects.equals(product.getPrice(), expPrice);
    }



    @Before
    public void setup() {
        this.itemLookupController = new ItemLookupController();
        this.productDbGateway = new ProductDbGateway();
        this.productDbGateway.fileReset();
        this.testProduct = new Product("Apple", 123456789123L, 5);
        this.productDbGateway.updateProduct(this.testProduct);
    }

    @Test
    public void testLookupByUPC() {
        assertTrue(checkAttributes(this.itemLookupController.lookupByUPC(123456789123L), "Apple", 123456789123L, 5));
        assertNull(this.itemLookupController.lookupByUPC(111111111111L));
    }

    @Test
    public void testLookupByName() {
        assertTrue(checkAttributes(this.itemLookupController.lookupByName("Apple"), "Apple", 123456789123L, 5));
        assertNull(this.itemLookupController.lookupByName("Orange"));
    }

}
