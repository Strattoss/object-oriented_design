package pl.edu.agh.internetshop.discount;

import pl.edu.agh.internetshop.PriceUtilities;

import java.math.BigDecimal;

public class Discount {
    private final BigDecimal discount;

    public Discount(BigDecimal discount) {
        if (discount.doubleValue() < 0.0 || discount.doubleValue() > 1.0) {
            throw new IllegalArgumentException("Discount value has to be between 0.0 and 1.0");
        }
        this.discount = PriceUtilities.scaleBigDecimal(discount);
    }

    public Discount() {
        this(BigDecimal.valueOf(0.0));
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal apply(BigDecimal price) {
        return price.multiply(PriceUtilities.intoScaledBigDecimal(1).subtract(discount));
    }
}
