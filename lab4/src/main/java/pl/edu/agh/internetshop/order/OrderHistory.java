package pl.edu.agh.internetshop.order;

import java.util.Collection;
import java.util.Iterator;

public class OrderHistory {
    private final Collection<Order> orders;

    public OrderHistory(Collection<Order> orders) {
        this.orders = orders;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public Iterator<Order> iterator() {
        return this.orders.iterator();
    }
}
