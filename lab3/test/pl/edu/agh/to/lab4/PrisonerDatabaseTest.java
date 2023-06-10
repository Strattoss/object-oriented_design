package pl.edu.agh.to.lab4;

import org.junit.Test;
import pl.edu.agh.to.lab4.dataproviding.PrisonersDataProvider;

import static org.junit.Assert.assertEquals;

public class PrisonerDatabaseTest {

    private PrisonersDataProvider prisonersDataProvider = new PrisonersDataProvider();

    @Test
    public void testThereAreThreeJails() {
        assertEquals(3, prisonersDataProvider.getAllPrisons().size());
    }
}
