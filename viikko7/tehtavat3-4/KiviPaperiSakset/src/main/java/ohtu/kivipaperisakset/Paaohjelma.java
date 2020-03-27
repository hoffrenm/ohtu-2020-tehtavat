package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        OUTER:
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            switch (scanner.nextLine()) {
                case "a":
                    PeliTehdas.pelaajaVsPelaaja().pelaa();
                    break;
                case "b":
                    PeliTehdas.pelaajaVsTekoaly().pelaa();
                    break;
                case "c":
                    PeliTehdas.pelaajaVsParempiTekoaly().pelaa();
                    break;
                default:
                    break OUTER;
            }
        }
    }
}
