/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

/**
 *
 * @author hoffrenm
 */
public class PeliTehdas {

    public static KPSPelaajaVsPelaaja pelaajaVsPelaaja() {
        return new KPSPelaajaVsPelaaja();
    }

    public static KPSTekoaly pelaajaVsTekoaly() {
        return new KPSTekoaly();
    }

    public static KPSParempiTekoaly pelaajaVsParempiTekoaly() {
        return new KPSParempiTekoaly();
    }
}
