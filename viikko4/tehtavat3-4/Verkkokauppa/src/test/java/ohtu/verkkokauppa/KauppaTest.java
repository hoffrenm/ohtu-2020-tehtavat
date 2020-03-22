package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa kauppa;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);

        kauppa = new Kauppa(varasto, pankki, viite);

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 9));

        when(varasto.saldo(3)).thenReturn(15);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "vanukas", 12));

        when(varasto.saldo(4)).thenReturn(0);
        when(varasto.haeTuote(4)).thenReturn(new Tuote(4, "popcorn", 5));

        kauppa.aloitaAsiointi();
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }

    @Test
    public void ostettaessaKahtaEriTuotettapankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 14);
    }

    @Test
    public void ostettaessaSamaaTuotettaKahdestipankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 24);
    }

    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeillaParametreillaKunKoriinLisataanVarastoOleviaSekaLoppuneitaTuotteita() {
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(4);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 12);
    }

    @Test
    public void asioinninAloittaminenAlustaaSession() {
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);

        kauppa.aloitaAsiointi();

        kauppa.lisaaKoriin(3);

        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 12);
    }

    @Test
    public void uusiViitenumeroJokaiselleOstokselle() {
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(viite, times(1)).uusi();

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("seppo", "54321");

        verify(viite, times(2)).uusi();
    }

    @Test
    public void lisatynTuotteenVoiPoistaa() {
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(1);

        kauppa.poistaKorista(3);
        kauppa.poistaKorista(5);

        kauppa.tilimaksu("makke", "45678");

        verify(varasto, times(5)).otaVarastosta(any());
        verify(varasto, times(2)).palautaVarastoon(any());
    }
}
