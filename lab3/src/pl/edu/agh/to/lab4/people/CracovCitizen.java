package pl.edu.agh.to.lab4.people;

public class CracovCitizen extends Suspect{

    private final int age;

    public CracovCitizen(String firstName, String lastName, int age) {
        super(firstName, lastName);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean canBeAccused() {
        return age >= 18;
    }

}
