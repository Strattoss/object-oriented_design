package pl.edu.agh.to.lab4.search;

import pl.edu.agh.to.lab4.people.Suspect;

import java.util.Collection;
import java.util.LinkedList;

public class CompositeSearchStrategy implements SearchStrategy{
    private final Collection<SearchStrategy> searchStrategies;

    public CompositeSearchStrategy(Collection<SearchStrategy> searchStrategies) {
        this.searchStrategies = searchStrategies;
    }

    public CompositeSearchStrategy() {
        this(new LinkedList<>());
    }

    public void addSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategies.add(searchStrategy);
    }

    @Override
    public boolean filter(Suspect suspect) {
        for (SearchStrategy searchStrategy : searchStrategies) {
            if (!searchStrategy.filter(suspect)) {
                return false;
            }
        }
        return true;
    }
}
