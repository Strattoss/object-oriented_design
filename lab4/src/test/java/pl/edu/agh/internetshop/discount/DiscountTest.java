package pl.edu.agh.internetshop.discount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import pl.edu.agh.internetshop.Product;
import pl.edu.agh.internetshop.PriceUtilities;
import pl.edu.agh.internetshop.order.OrderEntry;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DiscountTest {
    Product product = mock(Product.class);

    @Test
    public void testNoDiscount() {
        // given
        Discount discount = new Discount();
        BigDecimal price = PriceUtilities.intoScaledBigDecimal(2.51);
        given(product.getPrice()).willReturn(price);

        OrderEntry orderEntry = new OrderEntry(product, 1, discount);

        // when
        BigDecimal discountedPrice = orderEntry.getPrice();

        // then
        assertEquals(discountedPrice, price);
    }


    @Test
    public void testFullDiscount() {
        // given
        Discount discount = new Discount(BigDecimal.valueOf(1));
        BigDecimal price = PriceUtilities.intoScaledBigDecimal(2.51);
        given(product.getPrice()).willReturn(price);

        BigDecimal expectedPrice = PriceUtilities.intoScaledBigDecimal(0);

        OrderEntry orderEntry = new OrderEntry(product, 1, discount);

        // when
        BigDecimal discountedPrice = orderEntry.getPrice();

        // then
        assertEquals(discountedPrice, expectedPrice);
    }


    @Test
    public void testCorrectAndIncorrectDiscount() {
        // given

        // when

        // incorrect
        Executable runnable_1 = () -> new Discount(BigDecimal.valueOf(1.2));
        Executable runnable_2 =  () -> new Discount(BigDecimal.valueOf(-0.1));

        // correct
        Executable runnable_3 = () -> new Discount(BigDecimal.valueOf(0.0));
        Executable runnable_4 = () -> new Discount(BigDecimal.valueOf(-0.0));

        // then
        assertThrows(IllegalArgumentException.class, runnable_1);
        assertThrows(IllegalArgumentException.class, runnable_2);
        assertDoesNotThrow(runnable_3);
        assertDoesNotThrow(runnable_4);

    }


    @Test
    public void testDiscountRounding() {
        // given
        Discount discount = new Discount(BigDecimal.valueOf(0.5));
        BigDecimal price = PriceUtilities.intoScaledBigDecimal(2.51);
        given(product.getPrice()).willReturn(price);

        BigDecimal expectedPrice = PriceUtilities.intoScaledBigDecimal(1.26);

        OrderEntry orderEntry = new OrderEntry(product, 1, discount);

        // when
        BigDecimal discountedPrice = orderEntry.getPrice();

        // then
        assertEquals(discountedPrice, expectedPrice);
    }

    @Test
    // discount of 0.2 means 20% OFF OF the original price, not 20% OF the original price
    public void testDiscountIsDiscountNotNewPrice() {
        // given
        Discount discount = new Discount(BigDecimal.valueOf(0.2));
        BigDecimal price = PriceUtilities.intoScaledBigDecimal(2.00);
        given(product.getPrice()).willReturn(price);

        OrderEntry orderEntry = new OrderEntry(product, 5, discount);

        BigDecimal expectedPrice = PriceUtilities.intoScaledBigDecimal(8);

        // when
        BigDecimal discountedPrice = orderEntry.getPrice();

        // then
        assertEquals(discountedPrice, expectedPrice);
    }

    @Test
    public void testGetDiscount() {
        // given
        BigDecimal expectedDiscountValue = PriceUtilities.intoScaledBigDecimal(0.7);
        Discount discount = new Discount(expectedDiscountValue);

        // when
        BigDecimal actualDiscountValue =  discount.getDiscount();

        // then
        assertEquals(expectedDiscountValue, actualDiscountValue);
    }
}
