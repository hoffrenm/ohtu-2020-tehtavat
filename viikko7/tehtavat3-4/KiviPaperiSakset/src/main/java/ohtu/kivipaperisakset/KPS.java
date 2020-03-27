/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author hoffrenm
 */
public abstract class KPS {

    protected static final Scanner scanner = new Scanner(System.in);

    private Tuomari tuomari;
    protected String ekanSiirto;
    protected String tokanSiirto;

    public KPS() {
        this.tuomari = new Tuomari();
    }

    public void pelaa() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        kysySiirrot();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            paivitaTilanne();
            kysySiirrot();
        }

        lopeta();
    }

    private void paivitaTilanne() {
        tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
        System.out.println(tuomari);
        System.out.println();
    }

    private void lopeta() {
        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    private void kysySiirrot() {
        kysyEkanSiirto();
        kysyTokanSiirto();
    }

    private void kysyEkanSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = scanner.nextLine();
    }

    abstract protected void kysyTokanSiirto();

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
