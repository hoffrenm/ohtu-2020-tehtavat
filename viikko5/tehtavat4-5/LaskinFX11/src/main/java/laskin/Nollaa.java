/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author hoffrenm
 */
public class Nollaa extends Komento {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        muisti = sovellus.tulos();
        sovellus.nollaa();

        int tulos = sovellus.tulos();

        tuloskentta.setText(tulos + "");
        syotekentta.setText("");

        if (tulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }

    @Override
    public void peru() {
        sovellus.asetaArvo(muisti);
        tuloskentta.setText(muisti + "");
        syotekentta.setText("");
    }

}
