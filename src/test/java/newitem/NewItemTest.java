package newitem;

import database.ProductDbGateway;
import entities.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewItemTest {

    private NewItemController newItemController;
    private ProductDbGateway productDbGateway;

    public boolean checkAttributes(Product product, String expName, Long expUPC, int expPrice) {
        return Objects.equals(product.getName(), expName) & Objects.equals(product.getUPC(), expUPC) &
                Objects.equals(product.getPrice(), expPrice);
    }

    @Before
    public void setup() {
        this.newItemController = new NewItemController();
        this.productDbGateway = new ProductDbGateway();
        this.productDbGateway.fileReset();

        this.newItemController.addNewItem("Apple", 123456789123L, 5);
    }

    @Test
    public void testWriteToEmpty() {
        HashMap<Long, Product> products = this.productDbGateway.getAllProducts();

        assertEquals(products.size(), 1);
        assertTrue(checkAttributes(products.get(123456789123L), "Apple", 123456789123L, 5));
    }

    @Test
    public void testWritetoNonEmpty() {
        this.newItemController.addNewItem("Orange", 234567891234L, 3);
        HashMap<Long, Product> products = this.productDbGateway.getAllProducts();

        assertEquals(products.size(), 2);
        assertTrue(checkAttributes(products.get(123456789123L), "Apple", 123456789123L, 5));
        assertTrue(checkAttributes(products.get(234567891234L), "Orange", 234567891234L, 3));
    }
}
