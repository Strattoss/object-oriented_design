package pl.edu.agh.internetshop.order;

import pl.edu.agh.internetshop.discount.Discount;

import java.math.BigDecimal;

public abstract class AbstractOrderEntry {
    Discount discount;

    public AbstractOrderEntry(Discount discount) {
        this.discount = discount;
    }

    public AbstractOrderEntry() {
        this(new Discount());
    }

    public abstract BigDecimal getPrice();
}
