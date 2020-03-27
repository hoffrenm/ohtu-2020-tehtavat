package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPS {

    private Tekoaly tekoaly;

    public KPSTekoaly() {
        this.tekoaly = new Tekoaly();
    }

    @Override
    protected void kysyTokanSiirto() {
        tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
    }
}
