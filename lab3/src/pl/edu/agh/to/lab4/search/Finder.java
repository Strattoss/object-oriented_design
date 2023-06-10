package pl.edu.agh.to.lab4.search;

import pl.edu.agh.to.lab4.dataproviding.CompositeAggregate;
import pl.edu.agh.to.lab4.people.Suspect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Finder {
    private final CompositeAggregate compositeAggregate;

    public Finder(CompositeAggregate compositeAggregate) {
        this.compositeAggregate = compositeAggregate;
    }

    public Collection<Suspect> find(SearchStrategy searchStrategy) {
        ArrayList<Suspect> suspectedPeople = new ArrayList<>();

        Iterator<? extends Suspect> iterator = this.compositeAggregate.iterator();
        while (iterator.hasNext()) {
            Suspect suspect = iterator.next();
            if (suspect.canBeAccused() && searchStrategy.filter(suspect)) {
                suspectedPeople.add(suspect);
            }
        }

        return suspectedPeople;
    }

    public void findAndDisplay(SearchStrategy searchStrategy) {
        Collection<Suspect> suspectedPeople = this.find(searchStrategy);

        System.out.println("Znalazlem " + suspectedPeople.size() + " pasujacych podejrzanych!");
        for (Suspect n : suspectedPeople) {
            System.out.println(n.display());
        }

    }
}
