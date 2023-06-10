package pl.edu.agh.to.lab4.search;

import pl.edu.agh.to.lab4.people.Suspect;

import java.util.function.Predicate;

public class LastNameSearchStrategy implements SearchStrategy{
    private final Predicate<String> lastNamePredicate;

    public LastNameSearchStrategy(Predicate<String > lastNamePredicate) {
        this.lastNamePredicate = lastNamePredicate;
    }

    @Override
    public boolean filter(Suspect suspect) {
        return this.lastNamePredicate.test(suspect.getFirstName());
    }
}
