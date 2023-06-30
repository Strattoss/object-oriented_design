package druk;

import dokumenty.Faktura;
import dokumenty.Pozycja;

import java.util.Iterator;

public class DrukujFakturePoAngielsku extends DrukujFakture {
    @Override
    void drukujNaglowek(Faktura faktura) {
        System.out.println("=====================================================");
        System.out.println("Invoice date: " + faktura.getDataSprzedazy().toString());
        System.out.println("Issued to: " + faktura.getKontrahent());

    }

    @Override
    void drukujPozycje(Faktura faktura) {
        Iterator<Pozycja> iteratorPozycji = faktura.getIteratorPozycji();
        while (iteratorPozycji.hasNext()) {
            Pozycja pozycja = iteratorPozycji.next();
            System.out.println("Product: " + pozycja.getNazwa() + ", Quantity: " + pozycja.getIlosc() + ", Value: " + pozycja.getWartosc());
        }
    }

    @Override
    void drukujStopke(Faktura faktura) {
        System.out.println("Total amount: " + faktura.getSuma());
        System.out.println("=====================================================");
    }
}
