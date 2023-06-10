package pl.edu.agh.to.lab4.dataproviding;

import pl.edu.agh.to.lab4.people.Suspect;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class CompositeAggregate implements SuspectAggregate {
    private final Collection<SuspectAggregate> suspectAggregates;

    public CompositeAggregate() {
        this(new LinkedList<>());
    }

    public CompositeAggregate(Collection<SuspectAggregate> suspectAggregates) {
        this.suspectAggregates = suspectAggregates;
    }

    public void addSuspectAggregate(SuspectAggregate susAggr) {
        suspectAggregates.add(susAggr);
    }

    @Override
    public Iterator<? extends Suspect> iterator() {
        return new CompositeAggregateIterator();
    }

    private class CompositeAggregateIterator implements Iterator<Suspect> {
        private final Iterator<SuspectAggregate> suspectAggregateIterator;
        private Iterator<? extends Suspect> currentIterator;

        public CompositeAggregateIterator() {
            this.suspectAggregateIterator = suspectAggregates.iterator();
            this.currentIterator = Collections.emptyIterator();
        }

        @Override
        public boolean hasNext() {
            while (!currentIterator.hasNext()) {
                if (!suspectAggregateIterator.hasNext()) {
                    return false; // no more SuspectAggregates left
                }
                currentIterator = suspectAggregateIterator.next().iterator();
            }
            return true;
        }

        @Override
        public Suspect next() {
            if (!hasNext()) {
                return null;
            }
            return currentIterator.next();
        }
    }
}
