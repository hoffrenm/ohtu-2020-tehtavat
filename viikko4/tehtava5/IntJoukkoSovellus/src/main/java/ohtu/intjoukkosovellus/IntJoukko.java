package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int osoitin;    // Osoittaa taulukon seuraavaan tyhjään kohtaan

    public IntJoukko() {
        joukko = new int[KAPASITEETTI];
        Arrays.fill(joukko, 0);
        osoitin = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }

        joukko = new int[kapasiteetti];
        Arrays.fill(joukko, 0);
        osoitin = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }

        joukko = new int[kapasiteetti];
        Arrays.fill(joukko, 0);
        osoitin = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            joukko[osoitin] = luku;
            osoitin++;

            if (osoitin == joukko.length) {
                joukko = kasvataTaulukkoa(joukko);
            }

            return true;
        }

        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < osoitin; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }

        return false;
    }

    public boolean poista(int luku) {
        int kohta = -1;

        for (int i = 0; i < osoitin; i++) {
            if (luku == joukko[i]) {
                kohta = i;
                joukko[i] = 0;
                break;
            }
        }

        if (kohta != -1) {
            taytaTyhjaKohta(kohta);
            return true;
        }

        return false;
    }

    private void taytaTyhjaKohta(int kohdasta) {
        for (int j = kohdasta; j < osoitin - 1; j++) {
            vaihdaAlkiot(j);
        }

        osoitin--;
    }

    private void vaihdaAlkiot(int indeksi) {
        int apu = joukko[indeksi];
        joukko[indeksi] = joukko[indeksi + 1];
        joukko[indeksi + 1] = apu;
    }

    private int[] kasvataTaulukkoa(int[] taulukko) {
        int[] temp = taulukko;
        taulukko = new int[osoitin + kasvatuskoko];
        kopioiTaulukko(temp, taulukko);

        return taulukko;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return osoitin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < osoitin; i++) {
            sb.append(joukko[i]);

            if (i < osoitin - 1) {
                sb.append(", ");
            }
        }

        sb.append("}");

        return sb.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[osoitin];

        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukko[i];
        }

        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }

        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }

        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }
}
