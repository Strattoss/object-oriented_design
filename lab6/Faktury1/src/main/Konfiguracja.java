package main;

import druk.DrukujFakture;
import druk.DrukujFakturePoAngielsku;
import rabaty.ObliczCenePoRabacie;
import rabaty.ObliczCenePoRabacieLosowym;

public class Konfiguracja {
    private static Konfiguracja konfiguracja;

    public static Konfiguracja getInstance() {
        if (konfiguracja == null) {
            konfiguracja = new Konfiguracja();
        }
        return konfiguracja;
    }

    private double kwotaRabatu = 10;
    private double procentRabatu = 10;

    private Konfiguracja() {
    }

    public ObliczCenePoRabacie getObecnyRabat() {
        return new ObliczCenePoRabacieLosowym();
    }

    public double getKwotaRabatu() {
        return kwotaRabatu;
    }

    public double getProcentRabatu() {
        return procentRabatu;
    }

    public DrukujFakture getDrukujFakture() {
        return new DrukujFakturePoAngielsku();
    }
}
