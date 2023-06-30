package kategorie;

import magazyn.Towar;
import main.Konfiguracja;

import java.util.ArrayList;

public class Podkategoria extends Kompozyt {
    public Podkategoria(String name) {
        super(name);
    }

    public void wypiszWszystko(int wciecia) {
        wypiszNazwe(wciecia);
        wypiszTowary(wciecia);
    }
}
