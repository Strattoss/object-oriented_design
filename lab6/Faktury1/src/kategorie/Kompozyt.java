package kategorie;

import magazyn.Towar;

import java.util.ArrayList;

public abstract class Kompozyt {
    private String name;
    private final ArrayList<Towar> towary = new ArrayList<>();

    public Kompozyt(String name) {
        this.name = name;
    }

    public void dodajTowar(Towar towar) {
        towary.add(towar);
    }

    public void wypiszNazwe(int wciecia) {
        System.out.println("\t".repeat(wciecia) + name + ": ");
    }

    public void wypiszTowary(int wciecie) {
        for (Towar towar: towary) {
            System.out.println("\t".repeat(wciecie) + "  " + towar.getNazwa());
        }
    }

    public abstract void wypiszWszystko(int wciecia);
}
