package pl.edu.agh.internetshop.order;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.order.Order;
import pl.edu.agh.internetshop.order.OrderHistory;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class OrderHistoryTest {
    private final ArrayList<Order> noOrders = new ArrayList<>();

    Order order1 = mock(Order.class);
    Order order2 = mock(Order.class);
    private final ArrayList<Order> orders = new ArrayList<>(Arrays.asList(
            order1,
            order2
    ));

    @Test
    public void testGetOrdersThroughOrderHistory() {
        // given
        OrderHistory orderHistory = new OrderHistory(orders);
        OrderHistory emptyOrderHistory = new OrderHistory(noOrders);

        // when
        Collection<Order> actualOrders1 = orderHistory.getOrders();
        Collection<Order> actualOrders2 = emptyOrderHistory.getOrders();

        // then
        assertSame(actualOrders1, orders);
        assertSame(actualOrders2, noOrders);
    }

    @Test
    public void testIterator() {
        // given
        OrderHistory orderHistory = new OrderHistory(orders);

        // when
        Iterator<Order> iterator = orderHistory.iterator();

        // then
        assertSame(iterator.next(), orders.get(0));
        assertSame(iterator.next(), orders.get(1));
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
