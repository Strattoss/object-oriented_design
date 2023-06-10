package pl.edu.agh.internetshop.search;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.PriceUtilities;
import pl.edu.agh.internetshop.order.Order;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OrderPriceSearchStrategyTest {

    @Test
    public void testMatchingOrderPriceWithEmptyOrder() {
        // given
        Order emptyOrder = mock(Order.class);
        given(emptyOrder.getPrice()).willReturn(PriceUtilities.intoScaledBigDecimal(0.00));

        OrderPriceSearchStrategy matchOrderPrice = new OrderPriceSearchStrategy(price -> price.compareTo(PriceUtilities.intoScaledBigDecimal(100.00)) <= 0);

        // when
        boolean isMatched = matchOrderPrice.matchesCriteria(emptyOrder);

        // then
        assertTrue(isMatched);
    }

    @Test
    public void testMatchingOrderPrice() {
        // given
        Order order = mock(Order.class);
        given(order.getPrice()).willReturn(PriceUtilities.intoScaledBigDecimal(10.00));

        OrderPriceSearchStrategy matchOrderPrice = new OrderPriceSearchStrategy(price -> price.compareTo(PriceUtilities.intoScaledBigDecimal(100.00)) <= 0);

        // when
        boolean isMatched = matchOrderPrice.matchesCriteria(order);

        // then
        assertTrue(isMatched);
    }
}

