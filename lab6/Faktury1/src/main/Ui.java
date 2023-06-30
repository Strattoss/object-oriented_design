package main;

import dokumenty.Faktura;
import druk.Druk;
import druk.DrukujFakturePoAngielsku;
import druk.DrukujFakturePoPolsku;
import kategorie.Kategoria;
import kategorie.Podkategoria;
import magazyn.Towar;

import java.util.Calendar;

//ZEWNETRZNY RABAT


public class Ui {

    public static void main(String[] args) {
        Calendar teraz = Calendar.getInstance();

        //Tworzymy towary
        Towar t1 = new Towar(10, "buty");
        Towar t2 = new Towar(2, "skarpety");
        Towar t3 = new Towar(20, "koszula");
        Towar t4 = new Towar(30, "spodnie");
        Towar t5 = new Towar(40, "telefon");
        Towar t6 = new Towar(100, "laptop");

        //I przykladowa fakture
        Faktura f = new Faktura(teraz.getTime(), "Fido");
        f.dodajPozycje(t1, 3);
        f.dodajPozycje(t2, 5);

        //Wydrukuj fakture
        Konfiguracja.getInstance().getDrukujFakture().drukujFakture(f);

        //Tworzymy strukturę kategorii i podkategorii
        Kategoria ubrania = new Kategoria("Ubrania");
        ubrania.dodajTowar(t1);
        ubrania.dodajTowar(t2);
        ubrania.dodajTowar(t3);
        ubrania.dodajTowar(t4);

        Podkategoria akcesoria = new Podkategoria("Akcesoria");
        ubrania.addKompozyt(akcesoria);
        akcesoria.dodajTowar(t1);
        akcesoria.dodajTowar(t2);

        Podkategoria duzeUbrania = new Podkategoria("Duże ubrania");
        ubrania.addKompozyt(duzeUbrania);
        duzeUbrania.dodajTowar(t3);
        duzeUbrania.dodajTowar(t4);

        Kategoria elektronika = new Kategoria("Elektronika");
        elektronika.dodajTowar(t5);
        elektronika.dodajTowar(t6);

        Kategoria glowna = new Kategoria("Wszystko");
        glowna.addKompozyt(ubrania);
        glowna.addKompozyt(elektronika);
        glowna.dodajTowar(t1);
        glowna.dodajTowar(t2);
        glowna.dodajTowar(t3);
        glowna.dodajTowar(t4);
        glowna.dodajTowar(t5);
        glowna.dodajTowar(t6);

        //Wypisz drzewo kategorii i podkategorii
        glowna.wypiszWszystko(0);
    }
}
