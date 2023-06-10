package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.order.Order;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class OrderPriceSearchStrategy implements ISearchStrategy{
    private final Predicate<BigDecimal> predicate;

    public OrderPriceSearchStrategy(Predicate<BigDecimal> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean matchesCriteria(Order order) {
        return predicate.test(order.getPrice());
    }
}
