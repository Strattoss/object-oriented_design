package pl.edu.agh.internetshop.order;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.PriceUtilities;
import pl.edu.agh.internetshop.discount.Discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OrderEntryAggregatorTest {

    @Test
    public void testGetOrderEntriesThroughGetOrderEntries() {
        // given
        Collection<OrderEntry> actualOrderEntries = mock(Collection.class);
        OrderEntryAggregator orderEntryAggregator = new OrderEntryAggregator(actualOrderEntries);

        // when
        Collection<? extends AbstractOrderEntry> expectedOrderEntries = orderEntryAggregator.getOrderEntries();

        // then
        assertEquals(actualOrderEntries, expectedOrderEntries);
    }

    @Test
    public void testGetPriceOfEmptyOrderEntryAggregator() {
        // given
        Collection<OrderEntry> orderEntries = new ArrayList<>();
        OrderEntryAggregator orderEntryAggregator = new OrderEntryAggregator(orderEntries);

        BigDecimal expectedValue = PriceUtilities.intoScaledBigDecimal(0);

        // when
        BigDecimal actualValue = orderEntryAggregator.getPrice();

        // then
        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void testGetPriceOfOneOrderEntryAggregator() {
        // given
        OrderEntry orderEntry_1 = mock(OrderEntry.class);
        given(orderEntry_1.getPrice()).willReturn(PriceUtilities.intoScaledBigDecimal(1.00));

        Collection<OrderEntry> orderEntries = new ArrayList<>(Arrays.asList(orderEntry_1));
        OrderEntryAggregator orderEntryAggregator = new OrderEntryAggregator(orderEntries);

        BigDecimal expectedValue = PriceUtilities.intoScaledBigDecimal(1.00);

        // when
        BigDecimal actualValue = orderEntryAggregator.getPrice();

        // then
        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void testGetPriceOfMultipleOrderEntryAggregator() {
        // given
        OrderEntry orderEntry_1 = mock(OrderEntry.class);
        given(orderEntry_1.getPrice()).willReturn(BigDecimal.valueOf(1.0));
        OrderEntry orderEntry_2 = mock(OrderEntry.class);
        given(orderEntry_2.getPrice()).willReturn(BigDecimal.valueOf(2.0));
        OrderEntry orderEntry_3 = mock(OrderEntry.class);
        given(orderEntry_3.getPrice()).willReturn(BigDecimal.valueOf(0.15));

        Collection<OrderEntry> orderEntries = new ArrayList<>(Arrays.asList(orderEntry_1, orderEntry_2, orderEntry_3));
        OrderEntryAggregator orderEntryAggregator = new OrderEntryAggregator(orderEntries);

        BigDecimal expectedValue = BigDecimal.valueOf(3.15);

        // when
        BigDecimal actualValue = orderEntryAggregator.getPrice();

        // then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetDiscountedPrice() {
        // given
        Discount discount = new Discount(BigDecimal.valueOf(0.5));  // 50% off

        OrderEntry orderEntry_1 = mock(OrderEntry.class);
        given(orderEntry_1.getPrice()).willReturn(BigDecimal.valueOf(1.01));
        OrderEntry orderEntry_2 = mock(OrderEntry.class);
        given(orderEntry_2.getPrice()).willReturn(BigDecimal.valueOf(1.01));
        OrderEntry orderEntry_3 = mock(OrderEntry.class);
        given(orderEntry_3.getPrice()).willReturn(BigDecimal.valueOf(1.01));

        Collection<OrderEntry> orderEntries = new ArrayList<>(Arrays.asList(orderEntry_1, orderEntry_2, orderEntry_3));
        OrderEntryAggregator orderEntryAggregator = new OrderEntryAggregator(orderEntries, discount);

        BigDecimal expectedValue = BigDecimal.valueOf(1.52);

        // when
        BigDecimal actualValue = orderEntryAggregator.getPrice();

        // then
        assertEquals(expectedValue, actualValue);
    }
}
