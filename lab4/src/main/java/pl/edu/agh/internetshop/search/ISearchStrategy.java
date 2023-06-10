package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.order.Order;

public interface ISearchStrategy {
    boolean matchesCriteria(Order order);
}
