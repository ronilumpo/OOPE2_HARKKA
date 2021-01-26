package harjoitustyo.omalista;
import harjoitustyo.apulaiset.Ooperoiva;
import java.util.*;

/**
 * Konkreettinen OmaLista-luokka, joka periytyy LinkedList-luokasta.
 * Toteuttaa Ooperoiva-rajapinnan.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * </p>
 * @author Roni Lumpo (roni.lumpo@tuni.fi) 
 */  
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {
    
    /*
     * Metodit
     */

    /**
    * Listan alkioiden välille muodostuu kasvava suuruusjärjestys, jos lisäys
    * tehdään vain tällä operaatiolla, koska uusi alkion lisätään listalle siten,
    * että alkio sijoittuu kaikkien itseään pienempien tai yhtä suurien alkioiden
    * jälkeen ja ennen kaikkia itseään suurempia alkioita. Alkioiden suuruusjärjestys
    * selvitetään Comparable-rajapinnan compareTo-metodilla.
    * <p>
    * Jos parametri liittyy esimerkiksi kokonaislukuun 2 ja listan tietoalkiot ovat
    * [ 0, 3 ], on listan sisältö lisäyksen jälkeen [ 0, 2, 3 ], koska {@literal 0 < 2 < 3}.
    * <p>
    * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
    * toteuttaneen Comparable-rajapinnan.
    * @return true, jos lisäys onnistui. False, jos lisäys epäonnistui, koska uutta
    * alkiota ei voitu vertailla. Vertailu epäonnistuu, kun parametri on null-arvoinen
    * tai siihen liittyvällä oliolla ei ole vastoin oletuksia Comparable-rajapinnan
    * toteutusta.
    */
    @SuppressWarnings({"unchecked"})
    @Override
    public boolean lisaa(E uusi) {
        if (uusi != null && uusi instanceof Comparable) {
            //alustetaan muuttujat
            boolean onnistui = false;
            boolean menikovaliin = false;
            String alkio1 = uusi.toString();

            //laskuri pitää kirjaa OmaListan alkioista
            for (int i = 0; i < this.size(); i++) {
                //haetaan alkio ja vertaillaan sitä parametrina saatuun alkioon
                E apu = this.get(i);
                String alkio2 = apu.toString();
                int tulos = alkio2.compareTo(alkio1);
                //jos tulos on pienempää tai yhtäsuurta kuin 0
                //lisätään parametrina saatualkio ennen vertailun kohteena ollutta alkiota
                if (tulos >= 0) {
                    this.add(i, uusi);
                    onnistui = true;
                    menikovaliin = true;
                    return onnistui;
                }
            }
            //jos parametrina saatu alkio on suurempaa vertailussa
            //kuin kaikki listan muut alkiot, lisätään parametrina
            //saatu alkio loppuun
            if (menikovaliin == false) {
                this.add(uusi);
                onnistui = true;
                return onnistui;
            }
            else {
                return onnistui;
            }
        }
        else {
            return false;
        }
    }    

    /**
    * Poistaa listalta viitteet, jota liittyvät tietoalkioon, johon parametrina
    * annettu viite liittyy. Tosin sanoen listalta poistetaan viitteet x = get(ind),
    * joille lauseke "poistettava == get(ind)" on totta. Listalta poistetaan yleensä
    * joko yksi tai ei yhtään alkiota. Useita alkioita poistetaan, kun parametri liittyy
    * tietoalkioon, jonka kaksi tai useampaa viitettä jakaa. Listalla on jaettuja
    * alkioita esimerkiksi, jos viite on lisätty listalle monta kertaa.
    *
    * @param poistettava viite tietoalkioon.
    * @return listalta poistettujen viitteiden lukumäärä.
    * 
    *
    */
    @Override
    public int poista(E poistettava) {
        //alustetaan muuttujat
        int poistetut = 0;
        int koko = this.size();

        //lähdetään käymään listan lopusta alkio kerrallaan
        //vertaillen
        for (int i = koko - 1; i >= 0; i--) {
            //jos alkio on sama olio, mitä halutaan poistaa
            //niin poistetaan se ja kasvatetaan poistettujen määrää
            if (this.get(i) == poistettava) {
                this.remove(i);
                poistetut++;
            }
        }
        //palautetaan poistettujen alkioiden lukumäärä
        return poistetut;
    }

    
}   