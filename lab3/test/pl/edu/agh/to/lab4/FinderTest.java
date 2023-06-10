package pl.edu.agh.to.lab4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to.lab4.dataproviding.CompositeAggregate;
import pl.edu.agh.to.lab4.dataproviding.PersonDataProvider;
import pl.edu.agh.to.lab4.dataproviding.PrisonersDataProvider;
import pl.edu.agh.to.lab4.people.CracovCitizen;
import pl.edu.agh.to.lab4.people.Prisoner;
import pl.edu.agh.to.lab4.search.Finder;
import pl.edu.agh.to.lab4.search.FirstNameSearchStrategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FinderTest {
    // FinderTest structure has been modified so it matches Finder after code refactoring
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private PrintStream originalOut;
    private final ArrayList<CracovCitizen> allPersons = new ArrayList<>();
    private final HashMap<String, Collection<Prisoner>> prisoners = new HashMap<>();

    private final FirstNameSearchStrategy findJanSearchStrategy = new FirstNameSearchStrategy(name -> name.equals("Jan"));


    @Test
    public void testDisplayingNotJailedPrisoner() {
        addPrisoner("Wiezeienie stanowe", new Prisoner("Jan", "Więzień", "80210454337", 2000, 1));
        prepareFinder().findAndDisplay(findJanSearchStrategy);
        assertContentIsDisplayed("Jan Więzień");
    }

    @Test
    public void testDisplayingSuspectedPerson() {
        allPersons.add(new CracovCitizen("Jan", "Mieszkaniec", 20));
        prepareFinder().findAndDisplay(findJanSearchStrategy);
        assertContentIsDisplayed("Jan Mieszkaniec");
    }

    @Test
    public void testNotDisplayingTooYoungPerson() {
        allPersons.add(new CracovCitizen("Jan", "Mieszkaniec-Młody", 15));
        prepareFinder().findAndDisplay(findJanSearchStrategy);
        assertContentIsNotDisplayed("Jan Mieszkaniec-Młody");
    }

    @Test
    public void testNotDisplayingJailedPrisoner() {
        allPersons.add(new CracovCitizen("Jan", "Mieszkaniec2", 20));
        addPrisoner("Wiezeienie stanowe", new Prisoner("Jan", "Więzień", "80210454337", 2000, 50));
        prepareFinder().findAndDisplay(findJanSearchStrategy);
        assertContentIsNotDisplayed("Jan Więzień");
    }

    private void assertContentIsDisplayed(String expectedContent) {
        assertTrue("Application did not contain expected content: " + outContent.toString(), outContent.toString()
                .contains(expectedContent));
    }

    private void assertContentIsNotDisplayed(String expectedContent) {
        assertFalse("Application did contain expected content although it should not: " + outContent.toString(), outContent.toString()
                .contains(expectedContent));
    }

    @Before
    public void redirectSystemOut() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void resetSystemOut() {
        System.setOut(originalOut);
    }

    private void addPrisoner(String category, Prisoner prisoner) {
        if (!prisoners.containsKey(category))
            prisoners.put(category, new ArrayList<Prisoner>());
        prisoners.get(category).add(prisoner);
    }

    private CompositeAggregate prepareCompositeAggregate() {
        CompositeAggregate compositeAggregate = new CompositeAggregate();
        compositeAggregate.addSuspectAggregate(new PrisonersDataProvider(this.prisoners));
        compositeAggregate.addSuspectAggregate(new PersonDataProvider(this.allPersons));
        return compositeAggregate;
    }

    private Finder prepareFinder() {
        return new Finder(prepareCompositeAggregate());
    }
}
