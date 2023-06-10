package pl.edu.agh.to.lab4.dataproviding;

import pl.edu.agh.to.lab4.people.Suspect;

import java.util.Iterator;

public interface SuspectAggregate {
    Iterator<? extends Suspect> iterator();
}
