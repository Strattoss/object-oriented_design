package kategorie;

import magazyn.Towar;

import java.util.ArrayList;

public class Kategoria extends Kompozyt{
    ArrayList<Kompozyt> kompozyty = new ArrayList<>();

    public void addKompozyt(Kompozyt kompozyt) {
        kompozyty.add(kompozyt);
    }

    public Kategoria(String name) {
        super(name);
    }

    @Override
    public void wypiszWszystko(int wciecia) {
        wypiszNazwe(wciecia);
        wypiszTowary(wciecia);
        for (Kompozyt kompozyt: kompozyty) {
            kompozyt.wypiszWszystko(wciecia + 1);
        }
    }
}
