package pl.edu.agh.to.lab4.people;

import java.util.Iterator;

public abstract class Suspect {
    protected final String firstName;
    protected final String lastName;

    public Suspect(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String display() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public abstract int getAge();

    public abstract boolean canBeAccused();
}
