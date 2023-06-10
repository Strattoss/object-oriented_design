package pl.edu.agh.internetshop.search;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Product;
import pl.edu.agh.internetshop.order.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ProductNameStrategyTest {
    private static Product product1;
    private static Product product2;
    private static OrderEntry orderEntryProduct1;
    private static OrderEntry orderEntryProduct2;

    @BeforeEach
    void setUpProducts() {
        product1 = mock(Product.class);
        given(product1.getName()).willReturn("Product1");

        product2 = mock(Product.class);
        given(product2.getName()).willReturn("Product2");
    }

    @BeforeEach
    void setUpOrderEntries() {
        orderEntryProduct1 = mock(OrderEntry.class);
        given(orderEntryProduct1.getProduct()).willReturn(product1);
        orderEntryProduct2 = mock(OrderEntry.class);
        given(orderEntryProduct2.getProduct()).willReturn(product2);
    }

    @Test
    public void testMatchingProductNameWithEmptyOrder() {
        // given
        Collection<AbstractOrderEntry> noOrderEntries = new ArrayList<>(Collections.emptyList());

        OrderEntryAggregator orderEntryAggregator = mock(OrderEntryAggregator.class);
        given(orderEntryAggregator.getOrderEntries()).willAnswer(invocation -> noOrderEntries);

        Order emptyOrder = mock(Order.class);
        given(emptyOrder.getOrderEntries()).willReturn(orderEntryAggregator);

        ProductNameSearchStrategy matchProductName = new ProductNameSearchStrategy(productName -> productName.equals("Product1"));

        // when
        boolean isMatched = matchProductName.matchesCriteria(emptyOrder);

        // then
        assertFalse(isMatched);
    }
    @Test
    public void testMatchingProductNameWithOneEntryOrder() {
        // given
        Collection<AbstractOrderEntry> orderEntries = new ArrayList<>(Arrays.asList(orderEntryProduct1));

        OrderEntryAggregator orderEntryAggregator = mock(OrderEntryAggregator.class);
        given(orderEntryAggregator.getOrderEntries()).willAnswer(invocation -> orderEntries);

        Order order = mock(Order.class);
        given(order.getOrderEntries()).willReturn(orderEntryAggregator);

        ProductNameSearchStrategy matchProductName = new ProductNameSearchStrategy(productName -> productName.equals("Product1"));

        // when
        boolean isMatched = matchProductName.matchesCriteria(order);

        // then
        assertTrue(isMatched);
    }
    @Test
    public void testMatchingProductNameWithMultipleEntriesOrder() {
        // given
        Collection<AbstractOrderEntry> orderEntries = new ArrayList<>(Arrays.asList(orderEntryProduct1, orderEntryProduct2));

        OrderEntryAggregator orderEntryAggregator = mock(OrderEntryAggregator.class);
        given(orderEntryAggregator.getOrderEntries()).willAnswer(invocation -> orderEntries);

        Order order = mock(Order.class);
        given(order.getOrderEntries()).willReturn(orderEntryAggregator);

        ProductNameSearchStrategy matchProductName = new ProductNameSearchStrategy(productName -> productName.equals("Product1"));

        // when
        boolean isMatched = matchProductName.matchesCriteria(order);

        // then
        assertTrue(isMatched);
    }
}
