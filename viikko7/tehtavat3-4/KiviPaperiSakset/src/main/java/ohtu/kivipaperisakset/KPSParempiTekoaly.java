package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPS {

    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly() {
        this.tekoaly = new TekoalyParannettu(20);
    }

    @Override
    protected void kysyTokanSiirto() {
        tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
    }
}
