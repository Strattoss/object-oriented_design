package rabaty;

import main.Konfiguracja;

public class ObliczCenePoRabacieProcentowym implements ObliczCenePoRabacie {
    private final double procentZnizki;

    public ObliczCenePoRabacieProcentowym() {
        this.procentZnizki = Konfiguracja.getInstance().getProcentRabatu();
    }

    @Override
    public double obliczCenePoRabacie(double cena) {
        return cena * (100 - procentZnizki) / 100;
    }
}
