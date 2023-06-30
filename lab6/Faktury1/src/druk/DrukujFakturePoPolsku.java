package druk;

import dokumenty.Faktura;
import dokumenty.Pozycja;

import java.util.Iterator;

public class DrukujFakturePoPolsku extends DrukujFakture {
    @Override
    void drukujNaglowek(Faktura faktura) {
        System.out.println("=====================================================");
        System.out.println("Faktura z dnia: " + faktura.getDataSprzedazy().toString());
        System.out.println("Wystawiona dla: " + faktura.getKontrahent());

    }

    @Override
    void drukujPozycje(Faktura faktura) {
        Iterator<Pozycja> iteratorPozycji = faktura.getIteratorPozycji();
        while (iteratorPozycji.hasNext()) {
            Pozycja pozycja = iteratorPozycji.next();
            System.out.println("Towar: " + pozycja.getNazwa() + ", Ilosc: " + pozycja.getIlosc() + ", Wartosc: " + pozycja.getWartosc());
        }
    }

    @Override
    void drukujStopke(Faktura faktura) {
        System.out.println("Na kwote: " + faktura.getSuma());
        System.out.println("=====================================================");
    }
}
