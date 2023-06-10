package pl.edu.agh.to.lab4.people;

public class Student extends Suspect{
    private final String index;
    private final int age;

    public Student(String firstName, String lastName, int age, String index) {
        super(firstName, lastName);
        this.age = age;
        this.index = index;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public boolean canBeAccused() {
        return age >= 18;
    }

    public String getIndex() {
        return index;
    }
}
