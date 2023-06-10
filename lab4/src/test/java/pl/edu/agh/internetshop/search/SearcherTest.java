package pl.edu.agh.internetshop.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.order.Order;
import pl.edu.agh.internetshop.order.OrderHistory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class SearcherTest {
    private Searcher searcher;
    private OrderHistory orderHistory;
    private Order order1;
    private Order order2;

    @BeforeEach
    void setUp() {
        orderHistory = mock(OrderHistory.class);
        searcher = new Searcher(orderHistory);
        order1 = mock(Order.class);
        order2 = mock(Order.class);
    }

    @Test
    void testSearchWithMatchingOrder() {
        // given
        Collection<Order> allOrders = Arrays.asList(order1, order2);
        given(orderHistory.getOrders()).willReturn(allOrders);

        ISearchStrategy searchStrategy = mock(ISearchStrategy.class);
        given(searchStrategy.matchesCriteria(order1)).willReturn(true);
        given(searchStrategy.matchesCriteria(order2)).willReturn(false);

        // when
        Collection<Order> matchingOrders = searcher.search(searchStrategy);

        // then
        assertEquals(1, matchingOrders.size());
        assertTrue(matchingOrders.contains(order1));
    }

    @Test
    void testSearchWithNoMatchingOrder() {
        // given
        Collection<Order> allOrders = Arrays.asList(order1, order2);
        given(orderHistory.getOrders()).willReturn(allOrders);

        ISearchStrategy searchStrategy = mock(ISearchStrategy.class);
        given(searchStrategy.matchesCriteria(any(Order.class))).willReturn(false);

        // when
        Collection<Order> matchingOrders = searcher.search(searchStrategy);

        // then
        assertTrue(matchingOrders.isEmpty());
    }

    @Test
    void testSearchWithEmptyOrderHistory() {
        // given
        Collection<Order> emptyOrders = Collections.emptyList();
        given(orderHistory.getOrders()).willReturn(emptyOrders);

        ISearchStrategy searchStrategy = mock(ISearchStrategy.class);

        // when
        Collection<Order> matchingOrders = searcher.search(searchStrategy);

        // then
        assertTrue(matchingOrders.isEmpty());
    }
}
