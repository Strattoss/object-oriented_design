package pl.edu.agh.to.lab4;

import pl.edu.agh.to.lab4.dataproviding.CompositeAggregate;
import pl.edu.agh.to.lab4.dataproviding.PersonDataProvider;
import pl.edu.agh.to.lab4.dataproviding.PrisonersDataProvider;
import pl.edu.agh.to.lab4.dataproviding.StudentDataProvider;
import pl.edu.agh.to.lab4.search.*;

public class Application {

    public static void main(String[] args) {
        // aggregate Cracov citizens, prisoners and students as suspects
        CompositeAggregate compositeAggregate = new CompositeAggregate();
        compositeAggregate.addSuspectAggregate(new PrisonersDataProvider());
        compositeAggregate.addSuspectAggregate(new PersonDataProvider());
        compositeAggregate.addSuspectAggregate(new StudentDataProvider());

        Finder suspects = new Finder(compositeAggregate);

        // find all Januszes
        SearchStrategy findJanuszesSearchStrategy = new FirstNameSearchStrategy(name -> name.equals("Janusz"));
        suspects.findAndDisplay(findJanuszesSearchStrategy);
        System.out.println();

        // find all suspects whose age is in the [21, 30] interval
        CompositeSearchStrategy compositeSearchStrategy = new CompositeSearchStrategy();
        compositeSearchStrategy.addSearchStrategy(new AgeSearchStrategy(age -> age >= 21 && age <= 30));
        suspects.findAndDisplay(compositeSearchStrategy);
    }
}
