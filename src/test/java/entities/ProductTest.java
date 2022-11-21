package entities;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    public final Product product = new Product("Product1", 123456789123L, 50);

    @Test
    public void testGetters(){
        assertEquals("Product1", product.getName());
        assertEquals(123456789123L, product.getUPC());
        assertEquals(50, product.getPrice());
    }

    @Test
    public void testSetters(){
        product.setName("Product2");
        product.setUPC(987654321987L);
        product.setPrice(100);
        assertEquals("Product2", product.getName());
        assertEquals(987654321987L, product.getUPC());
        assertEquals(100, product.getPrice());
    }
}