package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.Product;
import pl.edu.agh.internetshop.order.Order;
import pl.edu.agh.internetshop.order.AbstractOrderEntry;
import pl.edu.agh.internetshop.order.OrderEntry;

import java.util.Iterator;
import java.util.function.Predicate;

public class ProductNameSearchStrategy implements ISearchStrategy{
    private final Predicate<String> predicate;

    public ProductNameSearchStrategy(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean matchesCriteria(Order order) {
        Product product;
        for (OrderEntry orderEntry : order.getOrderEntries().getOrderEntries()) {
            product = orderEntry.getProduct();
            if (predicate.test(product.getName())) {
                return true;
            }
        }
        return false;
    }
}
