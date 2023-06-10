package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceUtilitiesTest {
    @Test
    public void testScaleBigDecimal() {
        // given
        BigDecimal bigDecimal = BigDecimal.valueOf(1.235);

        // when
        bigDecimal = PriceUtilities.scaleBigDecimal(bigDecimal);

        // then
        assertEquals(bigDecimal.scale(), 2);
        assertEquals(bigDecimal.doubleValue(), 1.24);
    }

    @Test
    public void testIntoScaledBigDecimal() {
        // given
        BigDecimal bigDecimal = PriceUtilities.intoScaledBigDecimal(1.235);

        // when

        // then
        assertEquals(bigDecimal.scale(), 2);
        assertEquals(bigDecimal.doubleValue(), 1.24);
    }
}
