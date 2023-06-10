package pl.edu.agh.internetshop.order;

import pl.edu.agh.internetshop.Product;
import pl.edu.agh.internetshop.PriceUtilities;
import pl.edu.agh.internetshop.discount.Discount;

import java.math.BigDecimal;

public class OrderEntry extends AbstractOrderEntry {
    private final Product product;
    private final int numberOfProducts;

    public OrderEntry(Product product, int numberOfProducts, Discount discount) {
        super(discount);

        if (numberOfProducts <= 0) {
            throw new IllegalArgumentException("Number of products cannot be less than 1");
        }

        this.product = product;
        this.numberOfProducts = numberOfProducts;
    }

    public OrderEntry(Product product, int numberOfProducts) {
        this(product, numberOfProducts, new Discount());
    }

    public OrderEntry(Product product) {
        this(product, 1, new Discount());
    }

    public BigDecimal getPrice() {
        return PriceUtilities.scaleBigDecimal(discount.apply(this.product.getPrice().multiply(PriceUtilities.intoScaledBigDecimal(this.numberOfProducts))));
    }


    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public Product getProduct() {
        return product;
    }
}
