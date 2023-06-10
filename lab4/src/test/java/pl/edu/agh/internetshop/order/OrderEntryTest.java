package pl.edu.agh.internetshop.order;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Product;
import pl.edu.agh.internetshop.PriceUtilities;
import pl.edu.agh.internetshop.discount.Discount;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OrderEntryTest {

    @Test
    public void testGetProduct() {
        // given
        Product expectedProduct = mock(Product.class);
        OrderEntry orderEntry = new OrderEntry(expectedProduct);

        // when
        Product actualProduct = orderEntry.getProduct();

        // then
        assertSame(expectedProduct, actualProduct);
    }

    @Test
    public void testGetDefaultNumberOfProducts() {
        // given
        Product product = mock(Product.class);
        OrderEntry orderEntry = new OrderEntry(product);

        // when
        int numberOfProducts = orderEntry.getNumberOfProducts();

        // then
        assertEquals(numberOfProducts, 1);
    }

    @Test
    public void testGetCustomCorrectNumberOfProducts() {
        // given
        Product product = mock(Product.class);
        OrderEntry orderEntry_1 = new OrderEntry(product, 1);
        OrderEntry orderEntry_2 = new OrderEntry(product, 2);
        OrderEntry orderEntry_10 = new OrderEntry(product, 10);

        // when
        int numberOfProducts_1 = orderEntry_1.getNumberOfProducts();
        int numberOfProducts_2 = orderEntry_2.getNumberOfProducts();
        int numberOfProducts_10 = orderEntry_10.getNumberOfProducts();

        // then
        assertEquals(numberOfProducts_1, 1);
        assertEquals(numberOfProducts_2, 2);
        assertEquals(numberOfProducts_10, 10);
    }

    @Test
    public void testGetCustomIncorrectNumberOfProducts() {
        // given
        Product product = mock(Product.class);

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new OrderEntry(product, 0));
        assertThrows(IllegalArgumentException.class, () -> new OrderEntry(product, -1));
        assertThrows(IllegalArgumentException.class, () -> new OrderEntry(product, -5));
    }

    @Test
    public void testDefaultDiscount() {
        // given
        Product product = mock(Product.class);
        given(product.getPrice()).willReturn(PriceUtilities.intoScaledBigDecimal(3.00));
        OrderEntry defaultOrderEntry = new OrderEntry(product, 1);
        OrderEntry zeroDiscountOrderEntry = new OrderEntry(product, 1, new Discount());

        // when
        BigDecimal actualPrice_1 = defaultOrderEntry.getPrice();
        BigDecimal actualPrice_2 = zeroDiscountOrderEntry.getPrice();

        // then
        assertEquals(actualPrice_1, actualPrice_2);
    }

    @Test
    public void testCustomDiscount() {
        // given
        Product product = mock(Product.class);
        given(product.getPrice()).willReturn(PriceUtilities.intoScaledBigDecimal(5.00));
        OrderEntry orderEntry = new OrderEntry(product, 2, new Discount(BigDecimal.valueOf(0.8)));

        BigDecimal expectedPrice = PriceUtilities.intoScaledBigDecimal(2.0);

        // when
        BigDecimal actualPrice = orderEntry.getPrice();

        // then
        assertEquals(expectedPrice, actualPrice);
    }
}
