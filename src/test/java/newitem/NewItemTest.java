package newitem;

import database.ProductDbGateway;
import entities.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class NewItemTest {

    private NewItemController newItemController;

    private ProductDbGateway productDbGateway;


    public boolean checkAttributes(Product product, String expectedName, long expectedUPC, int expectedPrice) {
        return Objects.equals(product.getName(), expectedName) & Objects.equals(product.getUPC(), expectedUPC) &
                Objects.equals(product.getPrice(), expectedPrice);
    }

    @Before
    public void setup() {
        productDbGateway = new ProductDbGateway();
        productDbGateway.fileReset();
        this.newItemController = new NewItemController(new NewItemInteractor(new NewItemPresenter(new NewItemViewModel()), productDbGateway));
        newItemController.newItem("Product1", "123456789123", "50");

    }

    @Test
    public void testWriteToEmpty() {

        var products = productDbGateway.getAllProducts();

        assertEquals(1, products.size());
        assertTrue(checkAttributes(products.get(123456789123L), "Product1", 123456789123L,
                50));
    }

    @Test
    public void testWriteToNonEmpty() {

        newItemController.newItem("Product2", "987654321987", "100");
        var products = productDbGateway.getAllProducts();

        assertEquals(2, products.size());
        assertTrue(checkAttributes(products.get(123456789123L), "Product1", 123456789123L,
                50));
        assertTrue(checkAttributes(products.get(987654321987L), "Product2", 987654321987L,
                100));

    }
}