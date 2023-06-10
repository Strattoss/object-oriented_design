package pl.edu.agh.internetshop.search;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Buyer;
import pl.edu.agh.internetshop.order.Order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class BuyerLastNameSearchStrategyTest {
    @Test
    public void testMatchingLastName() {
        // given
        Buyer mockedBuyer1 = mock(Buyer.class);
        given(mockedBuyer1.getLastName()).willReturn("Johnson");
        Buyer mockedBuyer2 = mock(Buyer.class);
        given(mockedBuyer2.getLastName()).willReturn("d'Nick");

        Order order1 = mock(Order.class);
        given(order1.getBuyer()).willReturn(mockedBuyer1);
        Order order2 = mock(Order.class);
        given(order2.getBuyer()).willReturn(mockedBuyer2);

        BuyerLastNameSearchStrategy matchLastName = new BuyerLastNameSearchStrategy(lastName -> lastName.equals("Johnson"));

        // when
        boolean isMatched1 = matchLastName.matchesCriteria(order1);
        boolean isMatched2 = matchLastName.matchesCriteria(order2);

        // then
        assertTrue(isMatched1);
        assertFalse(isMatched2);
    }
}
