package pl.edu.agh.to.lab4;

import org.junit.Test;
import pl.edu.agh.to.lab4.people.Prisoner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrisonerTest {
    @Test
    public void testPrisonerIsInJail() {
        Prisoner news = new Prisoner("Jan", "Kowalski", "80210454337", 2011, 50);
        assertTrue(news.isJailedNow());
    }

    @Test
    public void testPrisonerHasBeenReleasedFromJail() {
        Prisoner news = new Prisoner("Jan", "Kowalski", "80210454337", 2008, 5);
        assertFalse(news.isJailedNow());
    }
}
