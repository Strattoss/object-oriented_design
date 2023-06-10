package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.Buyer;
import pl.edu.agh.internetshop.order.Order;

import java.util.function.Predicate;

public class BuyerLastNameSearchStrategy implements ISearchStrategy{
    private final Predicate<String> predicate;

    public BuyerLastNameSearchStrategy(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean matchesCriteria(Order order) {
        return predicate.test(order.getBuyer().getLastName());
    }
}
