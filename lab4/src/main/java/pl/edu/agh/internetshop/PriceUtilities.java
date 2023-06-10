package pl.edu.agh.internetshop;

import java.math.BigDecimal;

public class PriceUtilities {
    public static final int PRICE_PRECISION = 2;
    public static final int ROUND_STRATEGY = BigDecimal.ROUND_HALF_UP;

    public static BigDecimal intoScaledBigDecimal(double value) {
        return BigDecimal.valueOf(value).setScale(PRICE_PRECISION, ROUND_STRATEGY);
    }

    public static BigDecimal scaleBigDecimal(BigDecimal bigDecimal) {
        return bigDecimal.setScale(PRICE_PRECISION, ROUND_STRATEGY);
    }
}
