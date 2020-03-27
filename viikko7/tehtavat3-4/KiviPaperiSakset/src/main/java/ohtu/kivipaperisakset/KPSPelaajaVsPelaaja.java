package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KPS {

    @Override
    protected void kysyTokanSiirto() {
        System.out.print("Toisen pelaajan siirto: ");
        tokanSiirto = scanner.nextLine();
    }
}
