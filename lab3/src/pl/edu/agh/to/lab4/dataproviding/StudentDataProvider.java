package pl.edu.agh.to.lab4.dataproviding;

import pl.edu.agh.to.lab4.people.Student;
import pl.edu.agh.to.lab4.people.Suspect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StudentDataProvider implements SuspectAggregate{
    private final Collection<Student> students = new ArrayList<>();

    public StudentDataProvider() {
        students.add(new Student("Radek", "Radosny", 20, "419203"));
        students.add(new Student("Bartłomiej", "Żartłomiej", 19, "123456"));
        students.add(new Student("Kornelia", "Rechotka", 20, "654321"));
        students.add(new Student("Janusz", "Śmiechowski", 21, "987654"));
        students.add(new Student("Hanna", "Humorek", 22, "456789"));
        students.add(new Student("Wojciech", "Wesołowski", 20, "987123"));
        students.add(new Student("Zuzanna", "Śmiechuś", 19, "321654"));
        students.add(new Student("Feliks", "Szczęściarz", 22, "789123"));
        students.add(new Student("Agnieszka", "Śmieszewska", 20, "456321"));
        students.add(new Student("Karol", "Śmieszkowski", 21, "654987"));
        students.add(new Student("Wiktoria", "Śmiechowska", 19, "123789"));
    }

    public Collection<Student> getAllStudentsCitizens() {
        return this.students;
    }

    @Override
    public Iterator<? extends Suspect> iterator() {
        return students.iterator();
    }
}
