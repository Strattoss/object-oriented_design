package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.order.Order;

import java.util.Collection;

public class ComplexSearchStrategy implements ISearchStrategy {
    private final Collection<ISearchStrategy> searchStrategies;

    public ComplexSearchStrategy(Collection<ISearchStrategy> searchStrategies) {
        this.searchStrategies = searchStrategies;
    }

    public void addSearchStrategy(ISearchStrategy searchStrategy) {
        this.searchStrategies.add(searchStrategy);
    }

    @Override
    public boolean matchesCriteria(Order order) {
        for (ISearchStrategy searchStrategy: searchStrategies) {
            if (!searchStrategy.matchesCriteria(order)) {
                return false;
            }
        }
        return true;
    }
}
