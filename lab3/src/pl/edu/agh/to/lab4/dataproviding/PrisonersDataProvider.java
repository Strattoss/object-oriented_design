package pl.edu.agh.to.lab4.dataproviding;

import pl.edu.agh.to.lab4.people.Prisoner;
import pl.edu.agh.to.lab4.people.Suspect;

import java.util.*;

public class PrisonersDataProvider implements SuspectAggregate {

    private Map<String, Collection<Prisoner>> prisoners = new HashMap<String, Collection<Prisoner>>();

    public PrisonersDataProvider() {
        addPrisoner("Wiezienie krakowskie", new Prisoner("Jan", "Kowalski", "87080452357", 2005, 7));
        addPrisoner("Wiezienie krakowskie", new Prisoner("Anita", "Wiercipieta", "84080452357", 2009, 3));
        addPrisoner("Wiezienie krakowskie", new Prisoner("Janusz", "Zlowieszczy", "92080445657", 2001, 10));
        addPrisoner("Wiezienie przedmiejskie", new Prisoner("Janusz", "Zamkniety", "80210454335", 2010, 5));
        addPrisoner("Wiezienie przedmiejskie", new Prisoner("Adam", "Future", "88021604337", 2020, 5));
        addPrisoner("Wiezienie przedmiejskie", new Prisoner("Zbigniew", "Nienajedzony", "90051452335", 2011, 1));
        addPrisoner("Wiezienie centralne", new Prisoner("Jan", "Przedziwny", "91103145223", 2009, 4));
        addPrisoner("Wiezienie centralne", new Prisoner("Janusz", "Podejrzany", "85121212456", 2012, 1));
    }

    public PrisonersDataProvider(HashMap<String, Collection<Prisoner>> prisoners) {
        this.prisoners = prisoners;
    }

    public Map<String, Collection<Prisoner>> findAll() {
        return prisoners;
    }

    public Collection<String> getAllPrisons() {
        return prisoners.keySet();
    }

    private void addPrisoner(String category, Prisoner prisoner) {
        if (!prisoners.containsKey(category))
            prisoners.put(category, new ArrayList<Prisoner>());
        prisoners.get(category).add(prisoner);
    }

    @Override
    public Iterator<? extends Suspect> iterator() {
        return new PrisonerIterator();
    }

    private class PrisonerIterator implements Iterator<Prisoner> {
        private final Iterator<Collection<Prisoner>> collectionIterator;
        private Iterator<Prisoner> currentIterator;

        public PrisonerIterator() {
            collectionIterator = prisoners.values().iterator();
            currentIterator = Collections.emptyIterator();
        }

        @Override
        public boolean hasNext() {
            while (!currentIterator.hasNext()) {
                if (!collectionIterator.hasNext()) {
                    return false; // No more collections or suspects left
                }
                currentIterator = collectionIterator.next().iterator();
            }
            return true;
        }

        @Override
        public Prisoner next() {
            if (!hasNext()) {
                return null;
            }
            return currentIterator.next();
        }
    }
}
