package rabaty;

import rabatlosowy.LosowyRabat;

public class ObliczCenePoRabacieLosowym implements ObliczCenePoRabacie {
    double losowyRabat = new LosowyRabat().losujRabat();

    @Override
    public double obliczCenePoRabacie(double cena) {
        return cena * (1 - losowyRabat);
    }
}
