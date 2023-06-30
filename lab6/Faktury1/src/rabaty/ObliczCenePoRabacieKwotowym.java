package rabaty;

import main.Konfiguracja;

public class ObliczCenePoRabacieKwotowym implements ObliczCenePoRabacie {
    private final double kwotaZnizki;

    public ObliczCenePoRabacieKwotowym() {
        this.kwotaZnizki = Konfiguracja.getInstance().getKwotaRabatu();
    }

    @Override
    public double obliczCenePoRabacie(double cena) {
        return (cena - kwotaZnizki) >= 0 ? cena - kwotaZnizki : 0;
    }
}
