package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class BuyerTest {
    private final String firstName = "Jacek";
    private final String lastName = "Glina";
    private final Buyer buyer = new Buyer(firstName, lastName);

    @Test
    void testGetFirstName() {
        // given
        // an already created orderer

        // when
        String result = buyer.getFirstName();

        // then
        Assertions.assertEquals(firstName, result);
    }

    @Test
    void testGetLastName() {
        // given
        // an already created orderer

        // when
        String result = buyer.getLastName();

        // then
        Assertions.assertEquals(lastName, result);
    }

    @Test
    void testGetId() {
        // given
        // an already created orderer

        // when
        UUID result = buyer.getId();

        // then
        Assertions.assertNotNull(result);
    }

    @Test
    void testGeneratedIdIsUnique() {
        // given
        Buyer anotherBuyer = new Buyer("Hildegarda", "Liczykrupa");

        // when
        UUID id1 = buyer.getId();
        UUID id2 = anotherBuyer.getId();

        // then
        Assertions.assertNotEquals(id1, id2);
    }
}

