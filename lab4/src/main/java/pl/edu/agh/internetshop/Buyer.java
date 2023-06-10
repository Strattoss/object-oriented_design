package pl.edu.agh.internetshop;

import java.util.UUID;

public class Buyer {
    private final String firstName;
    private final String lastName;
    private final UUID id;

    public Buyer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = UUID.randomUUID();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getId() {
        return id;
    }
}
