package pl.edu.agh.to.lab4.search;

import pl.edu.agh.to.lab4.people.Suspect;

import java.util.function.Predicate;

public class FirstNameSearchStrategy implements SearchStrategy{
    private final Predicate<String> firstNamePredicate;

    public FirstNameSearchStrategy(Predicate<String > firstNamePredicate) {
        this.firstNamePredicate = firstNamePredicate;
    }

    @Override
    public boolean filter(Suspect suspect) {
        return this.firstNamePredicate.test(suspect.getFirstName());
    }
}
