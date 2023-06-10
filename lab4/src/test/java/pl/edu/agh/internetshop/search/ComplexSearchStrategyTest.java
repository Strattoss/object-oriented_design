package pl.edu.agh.internetshop.search;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.order.Order;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ComplexSearchStrategyTest {

    private ComplexSearchStrategy complexSearchStrategy;
    private ISearchStrategy searchStrategy1;
    private ISearchStrategy searchStrategy2;

    @BeforeEach
    void setUp() {
        complexSearchStrategy = new ComplexSearchStrategy(new ArrayList<>());
        searchStrategy1 = mock(ISearchStrategy.class);
        searchStrategy2 = mock(ISearchStrategy.class);
    }

    @Test
    void testMatchesCriteria_AllStrategiesMatch_ReturnsTrue() {
        // given
        Order order = mock(Order.class);
        given(searchStrategy1.matchesCriteria(order)).willReturn(true);
        given(searchStrategy2.matchesCriteria(order)).willReturn(true);

        complexSearchStrategy.addSearchStrategy(searchStrategy1);
        complexSearchStrategy.addSearchStrategy(searchStrategy2);

        // when
        boolean result = complexSearchStrategy.matchesCriteria(order);

        // then
        assertTrue(result);
    }

    @Test
    void testMatchesCriteria_AtLeastOneStrategyDoesNotMatch_ReturnsFalse() {
        // given
        Order order = mock(Order.class);
        given(searchStrategy1.matchesCriteria(order)).willReturn(true);
        given(searchStrategy2.matchesCriteria(order)).willReturn(false);

        complexSearchStrategy.addSearchStrategy(searchStrategy1);
        complexSearchStrategy.addSearchStrategy(searchStrategy2);

        // when
        boolean result = complexSearchStrategy.matchesCriteria(order);

        // then
        assertFalse(result);
    }

    @Test
    void testMatchesCriteria_NoStrategies_ReturnsTrue() {
        // given
        Order order = mock(Order.class);

        // when
        boolean result = complexSearchStrategy.matchesCriteria(order);

        // then
        assertTrue(result);
    }
}

