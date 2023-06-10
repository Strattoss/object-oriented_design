package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.order.Order;
import pl.edu.agh.internetshop.order.OrderHistory;

import java.util.ArrayList;
import java.util.Collection;

public class Searcher{
    private final OrderHistory orderHistory;

    public Searcher(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    public Collection<Order> search(ISearchStrategy searchStrategy) {
        ArrayList<Order> matchingOrders = new ArrayList<>();

        for (Order order: orderHistory.getOrders()) {
            if (searchStrategy.matchesCriteria(order)) {
                matchingOrders.add(order);
            }
        }

        return matchingOrders;
    }
}
