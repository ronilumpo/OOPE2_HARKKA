package harjoitustyo.tiedot;
import harjoitustyo.apulaiset.Syvakopioituva;
import java.io.*;

/**
 * Konkreettinen Tiedosto-luokka, joka periytyy Tieto-luokasta.
 * Toteuttaa Syvakopioituva- ja
 * Serializable-rajapinnat.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * </p>
 * @author Roni Lumpo (roni.lumpo@tuni.fi) 
 */  
public class Tiedosto extends Tieto implements Syvakopioituva<Tiedosto>, Serializable {

    /*
     * Attribuutit
     */
    
    /** Atrribuutti, joka kertoo tiedoston koon bitteinä */
    private int koko;
    
    /*
     * Rakentajat
     */ 

    public Tiedosto() {
        koko = 0;
    }

    /**
     * Tiedosto-luokan parametrillinen rakentaja.
     * 
     * @param alkuNimi Tiedoston nimi
     * @param alkuKoko Tiedoston koko
     * @throws IllegalArgumentException jos nimi ei ole välillä a-z, A-Z tai 0-9 poislukien merkit
     * "_" ja ".", kunhan
     * nimi ei koostu ainoastaan merkeistä tai jos koko on pienempää kuin 0.
     * 
     */
    public Tiedosto(StringBuilder alkuNimi, int alkuKoko) throws IllegalArgumentException {
        super(alkuNimi);
        koko(alkuKoko);
    }
    
    /*
     * Aksessorit
     */

    public int koko() {
        return koko;
    }

    /**
     * Tiedosto-luokan koko-attribuutin parametrillinen rakentaja.
     * 
     * @param uusiKoko uusi koko
     * @throws IllegalArgumentException jos koko on pienempää kuin 0
     */
    public void koko(int uusiKoko) throws IllegalArgumentException {
        if (uusiKoko >= 0) {
            koko = uusiKoko;
        }
        else {
            throw new IllegalArgumentException("Error!");
        }
    } 
     
    /*
     * Metodit
     */ 

    /**
     * Object-luokan toString-metodin korvaus Hakemisto-luokassa
     * 
     * Kutsuu yliluokassa korvattua toString-metodia, lisää välin 
     * sekä koko-attribuutin arvon ja palauttaa nämä tiedot 
     * @return merkkijonoesitys
     */ 
    @Override
    public String toString() {
        //kutsutaan yliluokan toString-operaatiota ja
        //lisätään sen perään Tiedosto-luokan oman attribuutin (koko) arvo.
        return super.toString() + " " + koko;
    }   

    /**
     * Syvakopioituva-rajapinnan korvattu metodi Tiedosto-luokassa.
     * Kopioi tiedoston ja asettaa kopioidulle tiedostolle oletuksen saman nimen, kuin alkuperäisellä
     * tiedostolla
     * @return viite kopioituun tiedostoon
     */
    @Override
    public Tiedosto kopioi() {
        try {
           // Byte-tyyppisten alkioiden (tavujen) taulukkoon kirjoittava virta.
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
   
           // Olion tavuiksi muuntava virta, joka liittyy taulukkoon kirjoittavaan
           // virtaan.
           ObjectOutputStream oos = new ObjectOutputStream(bos);
   
           // Kirjoitetaan olio tavumuodossa taulukkoon.
           oos.writeObject(this);
   
           // Tyhjennetään puskuri ja suljetaan virta.
           oos.flush();
           oos.close();
   
           // Liitetään taulukkoon tavuja lukeva syötevirta.
           ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
   
           // Tavut olioksi muuttava virta, joka liittyy taulukosta lukevaan virtaan.
           ObjectInputStream ois = new ObjectInputStream(bis);
   
           // Kopio saadaan aikaiseksi lukemalla olion tavut taulukosta.
           Object kopio = ois.readObject();

           //muunnetaan kopioitu olio Tiedostoksi
           Tiedosto uusiKopio = (Tiedosto)kopio;

           //annetaan kopiolle sama nimi, kuin alkuperäisellä tiedostolla
           StringBuilder kopioNimi = new StringBuilder(this.nimi());
           uusiKopio.nimi(kopioNimi);
   
           // Palautetaan oikean tyyppinen viite.
           return uusiKopio;
        }
        // Sarjallistettavan olion oletusrakentaja hukassa.
        catch (InvalidClassException e) {
           return null;
        }
        // Löytyi olio, joka ei sarjallistu.
        catch (NotSerializableException e) {
           return null;         
        }
        // Tapahtui jotain yllättävää.
        catch (Exception e) {
           return null;
        }
    }
} 