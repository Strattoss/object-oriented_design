package pl.edu.agh.to.lab4.search;

import pl.edu.agh.to.lab4.people.Suspect;

import java.util.function.Predicate;

public class AgeSearchStrategy implements SearchStrategy{
    private final Predicate<Integer> agePredicate;

    public AgeSearchStrategy(Predicate<Integer> agePredicate) {
        this.agePredicate = agePredicate;
    }

    @Override
    public boolean filter(Suspect suspect) {
        return agePredicate.test(suspect.getAge());
    }
}
