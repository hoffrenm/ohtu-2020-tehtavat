package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPSAI {

    public KPSParempiTekoaly() {
        this.tekoaly = new TekoalyParannettu(20);
    }
}
