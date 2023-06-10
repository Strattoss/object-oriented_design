package pl.edu.agh.internetshop.order;

import pl.edu.agh.internetshop.PriceUtilities;
import pl.edu.agh.internetshop.discount.Discount;

import java.math.BigDecimal;
import java.util.Collection;

public class OrderEntryAggregator extends AbstractOrderEntry {
    private final Collection<OrderEntry> orderEntries;

    public OrderEntryAggregator(Collection<OrderEntry> orderEntries, Discount discount) {
        super(discount);
        this.orderEntries = orderEntries;
    }

    public OrderEntryAggregator(Collection<OrderEntry> orderEntries) {
        this(orderEntries, new Discount());
    }
    public Collection<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal result = BigDecimal.valueOf(0.0);
        for (AbstractOrderEntry orderEntry: this.orderEntries) {
            result = result.add(orderEntry.getPrice());
        }
        return PriceUtilities.scaleBigDecimal(discount.apply(result));
    }
}
