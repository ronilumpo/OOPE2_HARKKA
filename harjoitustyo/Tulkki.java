package harjoitustyo;
import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tieto;
import harjoitustyo.tiedot.Tiedosto;
import java.util.*;
import harjoitustyo.iteraattorit.*;

/**
 * Konkreettinen Tulkki-luokka, joka vastaanottaa tietoja TUI-luokalta ja
 * käsittelee saamansa tiedot
 * @author Roni Lumpo (roni.lumpo@tuni.fi)
 */
public class Tulkki {

   /*
    * Attribuutit
    */

   /** Attribuutti, joka on juurihakemiston ilmentymä */ 
   public Hakemisto juuri;

   /** Attribuutti, joka on pitää kirjaa nykyisestä hakemistosta */
   public Hakemisto nykyinen;

   /*
    * Rakentajat
    */   

   public Tulkki() {
      juuri = new Hakemisto();
      nykyinen = juuri;
   }

   /*
    * Aksessorit
    */

   public void juuri(Hakemisto uusiJuuri) {
      if (uusiJuuri != null) {
         juuri = uusiJuuri;
      }
   }   
   
   public Hakemisto juuri() {
      return juuri;
   }

   public void nykyinen(Hakemisto uusiHakemisto) {
      if (uusiHakemisto != null) {
         nykyinen = uusiHakemisto;
      }
   }

   public Hakemisto nykyinen() {
      return nykyinen;
   }


   /*
    * Metodit  
    */

   /**
    * Metodi, joka luo uuden hakemiston
    * @param nimi merkkijono, joka asetetaan kansion nimeksi
    * kansion nimen oikeellisuus tarkistetaan Hakemisto-luokan aksessorissa
    * @throws IllegalArgumentException mikäli parametri on null tai
    * hakemistossa on jo samanniminen tieto-luokan ilmentymä
    */ 
   public void luoHakemisto(String nimi) throws IllegalArgumentException{
      if (nimi != null) {
         int samat = 0;
         //jos nykyhakemistossa on samanniminen Tieto-luokan ilmentymä,
         //ei luoda uutta hakemistoa
         for (int p = 0; p < nykyinen.sisalto().size(); p++) {
            Tieto alkio = (Tieto)nykyinen.sisalto().get(p);
            String a = alkio.nimi().toString();
            if (a.equals(nimi)) {
               samat++;
            }
         }
         if (samat == 0) {
            StringBuilder nimi1 = new StringBuilder(nimi);
            Hakemisto uusi = new Hakemisto(nimi1, nykyinen);
            nykyinen.lisaa(uusi);
         }
         else {
            throw new IllegalArgumentException("Error!");
         }
      }
      else {
         throw new IllegalArgumentException("Error!");
      }   
   }

   /**
    * Metodi, joka vaihtaa hakemiston yli- tai alihakemistoksi riippuen syötteestä.
    * @param nimi voi olla kansion nimi, ".." tai tyhjä merkkijono.
    * Mikäli parametri on tyhjä merkkijono, niin ohjelma palaa juurihakemistoon
    * @throws IllegalArgumentException jos merkkijono ei ole tyhjä, ".." tai 
    * täsmää mihinkään alihakemistoon
    */
   public void hakemistoVaihto(String nimi) throws IllegalArgumentException {
      //jos parametrina saatu kansion nimi on "..", niin palataan ylihakemistoon
      if (nimi.equals("..")) {
         if (nykyinen != juuri) {
            nykyinen = nykyinen.ylihakemisto();
         }
         else {
            System.out.println("Error!");
         }   
      }
      //jos paramterina saatu merkkijono on tyhjä, palataan juurihakemistoon
      else if (nimi.equals("")) {
         boolean jatketaan = true;
         //silmukoidaan juurihakemistoon
         while(jatketaan) {
            if (nykyinen.ylihakemisto() != null) {
               nykyinen = nykyinen.ylihakemisto();
            }
            else {
               jatketaan = false;
            }
         }
      }
      //mikäli parametrina saatu kansion nimi löytyy nykyhakemiston alihakemistosta,
      //siirrytään sinne
      else if (nykyinen.hae(nimi) != null) {
         LinkedList<Tieto> hakemistot = nykyinen.hae(nimi);
         nykyinen = (Hakemisto)hakemistot.getFirst();

      }
      //mikäli parametrina saatu merkkijono ei täsmää vaihtoehtoihin, heitetään poikkeus
      else {
         throw new IllegalArgumentException("Error!");
      }
   }

   /**
    *  Metodi, joka luo uuden tiedoston 
    * @param nimi tiedoston nimi, oikeellisuus tarkistetaan Tiedosto-luokan aksessorissa
    * @param koko tiedoston koko, oikeellisuus tarkistetaan Tiedosto-luokan aksessorissa
    * @throws IllegalArgumentException jos nykyisessä kansiossa on jo samanlainen tiedosto
    * tai parametri on null
    */
   public void luoTiedosto(String nimi, int koko) throws IllegalArgumentException {
      if (nimi != null) {
         StringBuilder nimi1 = new StringBuilder(nimi);
         Tiedosto uusi = new Tiedosto(nimi1, koko);
         if (!nykyinen.sisalto().contains(uusi)) {
            nykyinen.lisaa(uusi);
         }
         else {
            throw new IllegalArgumentException("Error!");
         }
      }
      else {
         throw new IllegalArgumentException("Error!");
      }  
   }

   /**
    * Metodi, joka nimeää tiedoston uudelleen
    * @param tiedostoNimi haettava tiedosto
    * @param uusiNimi tiedoston uusi nimi, oikeellisuus tarkistetaan 
    * Tiedosto-luokan aksessorissa
    * @throws IllegalArgumentException mikäli hakemistossa on jo samanniminen tiedosto,
    * tai jos jokin parametreista on null
    */
   public void nimeaUudelleen(String tiedostoNimi, String uusiNimi) throws IllegalArgumentException{
      if (tiedostoNimi != null && uusiNimi != null) {
         //haetaan haluttu tieto      
         LinkedList<Tieto> lista = nykyinen.hae(tiedostoNimi);
         Tieto vaihdaNimi = lista.getFirst();
         int samat = 0;
         //tarkistetaan onko nykyhakemistossa saman nimistä tiedostoa,
         //kuin miksi halutun tiedon nimi halutaan muuttaa
         for (int p = 0; p < nykyinen.sisalto().size(); p++) {
            Tieto alkio = (Tieto)nykyinen.sisalto().get(p);
            String a = alkio.nimi().toString();
            if (a.equals(uusiNimi)) {
               samat++;
            }
         }
         //jos samoja ei löytynyt, voidaan tiedon nimi muuttaa
         if (samat == 0) {
            StringBuilder uusiNimi1 = new StringBuilder(uusiNimi);
            vaihdaNimi.nimi(uusiNimi1);
            nykyinen.poista(vaihdaNimi);
            nykyinen.lisaa(vaihdaNimi);
         }
         else {
            throw new IllegalArgumentException("Error!");
         }
         
      }
      else {
         throw new IllegalArgumentException("Error!");
      }
      
   }

   /**
    * Metodi, joka listaa nykyhakemiston sisällön
    * @param hakemistoNimi hakusana, jolla on mahdollista rajata listattavia tietoja
    * @return palauttaa löydetyt tiedot linkitettynä listana
    * @throws IllegalArgumentException jos kohteita ei löydy tai listaus kohdistuu tyhjään listaan 
    * poislukien parametriton listaus sekä listaus, jonka parametrina on ainoastaan jokerimerkki
    */
   public LinkedList<Tieto> listaa(String hakemistoNimi) throws IllegalArgumentException { 
      if (hakemistoNimi != null) {
         LinkedList<Tieto> palauta = nykyinen.hae(hakemistoNimi);
         //jos hakemiston sisältö on tyhjä, mutta haussa on käytetty jokerimerkkiä,
         //tai haku on parametriton, ei tulosteta virheilmoitusta
         if (palauta.size() == 0) {
            if (hakemistoNimi.equals("*") || hakemistoNimi.equals("")) {
               return palauta;
            }
            else {
               throw new IllegalArgumentException("Error!");
            }   
         }
         return palauta;
      }
      //jos parametri on null, heitetään poikkeus
      else {
         throw new IllegalArgumentException("Error!");
      }
      

   }

   /**
    * Metodi joka poistaa nykyhakemistossa olevia tiedostoja tai hakemistoja
    * @param tietoNimi poistettavan tiedon nimi
    * @throws IllegalArgumentException jos poistettavia ei löydy
    */
   public void poista(String tietoNimi) throws IllegalArgumentException {
      if (tietoNimi != null) {
         int poistetut = 0;
         LinkedList<Tieto> poistettavat = nykyinen.hae(tietoNimi);
         //käydään läpi kaikki poistettavat tiedostot
         for (int i = 0; i < poistettavat.size(); i++) {
            nykyinen.poista(poistettavat.get(i));
            poistetut++;
         }
         if (poistetut == 0) {
            throw new IllegalArgumentException("Error!");
         }
      }
      else {
         throw new IllegalArgumentException("Error!");
      }
      
   }

   /**
    * Metodi, jolla on mahdollista kopioida nykyhakemistossa sijaitsevan tiedoston, kolmella tapaa
    * 1. Tiedoston x kopiointi uudella nimellä y
    * 2. Tiedoston x kopiointi samalla nimellä välittömään yli- tai alikansioon
    * 3. Tiedostojen x-n kopiointi samalla nimellä välittömään yli- tai alikansioon
    * @param tiedostoNimi kopioitavan tiedoston nimi
    * @param tietoTaiKansio kopioitavan tiedoston uusi nimi tai kopioinnin kohteen(kansion) nimi
    * @throws IllegalArgumentException jos kopiointihakemisto sisältää jo tiedoston,
    * jolla on sama nimi kuin kopioidulla tiedostolla
    */
   public void kopioi(String tiedostoNimi, String tietoTaiKansio) throws IllegalArgumentException {
      int i;
      int kopioidut = 0;

      //käydään läpi nykyhakemiston alkiot
      for (i = 0; i < nykyinen.sisalto().size(); i++) {
         Tieto haku = nykyinen.sisalto().get(i);
         String osa = "";
         //jos ei olla juurihakemistossa, haetaan ylihakemiston nimi
         if (nykyinen.ylihakemisto() != null) {
         String ylihak = nykyinen.ylihakemisto().toString();
         String[] osat = ylihak.split("/");
         osa = osat[0];
         }

         String[] hakem = haku.toString().split("/");
         //jos parametrina saatu kansio vastaa nykyhakemiston ylihakemistoa,
         //kopioidaan tiedosto sinne
         if (tietoTaiKansio != null && (osa.equals(tietoTaiKansio)) || tietoTaiKansio.equals("..")) { 
            int a;
            //käydään läpi hakemiston sisällön alkiot ja verrataan jokaista alkiota
            //annettuun parametriin
            for (a = 0; a < nykyinen.sisalto().size(); a++) {
               Tieto kopioitava1 = (Tieto)nykyinen.sisalto().get(a);
               //jos löytyi haluttu alkio, kopioidaan se
               if (kopioitava1.equals(tiedostoNimi)) {
                  Tiedosto kopioitava = (Tiedosto)kopioitava1;
                  Tiedosto kopioitu1 = kopioitava.kopioi();
                  Hakemisto paikka = nykyinen.ylihakemisto();
                  //mikäli ylihakemistossa ei ole samannimistä tiedostoa,
                  //voidaan kopioitu tiedosto siirtää sinne
                  if (!paikka.sisalto().contains(kopioitu1)) {
                     paikka.lisaa(kopioitu1);
                     kopioidut++;
                  }  
               } 
            }     
         }
         //jos parametrina saatu kansio vastaa nykyhakemiston alihakemistoa, 
         //kopioidaan tiedosto sinne
         else if (tietoTaiKansio != null && hakem[0].equals(tietoTaiKansio)) {
            int a;
            //käydään läpi hakemiston sisällön alkiot ja verrataan jokaista alkiota
            //annettuun parametriin
            for (a = 0; a < nykyinen.sisalto().size(); a++) {
               Tieto kopioitava1 = (Tieto)nykyinen.sisalto().get(a);
               //jos löytyi haluttu alkio, kopioidaan se
               if (kopioitava1.equals(tiedostoNimi)) {
                  Tiedosto kopioitava = (Tiedosto)kopioitava1;
                  Tiedosto kopioitu1 = kopioitava.kopioi();
                  Hakemisto paikka = (Hakemisto)haku;
                  //jos alihakemistossa ei ole samannimistä tiedostoa,
                  //voidaan kopioitu tiedosto siirtää sinne
                  if (!paikka.sisalto().contains(kopioitu1)) {
                     paikka.lisaa(kopioitu1);
                     kopioidut++;
                  } 
               }         
            }   
         }
      }
      //jos mikään aiemmista ei toiminut, kokeillaan kopioimista uudella nimellä samaan kansioon
      if (kopioidut == 0 && tietoTaiKansio != null && tiedostoNimi.charAt(0) != '*' &&
      tiedostoNimi.charAt(tiedostoNimi.length() - 1) != '*') {
         //haetaan tiedosto nykyhakemistosta
         LinkedList<Tieto> uusi = nykyinen.hae(tiedostoNimi);
         Tiedosto kopioitava2 = (Tiedosto)uusi.getFirst();
         //kopioidaan se
         Tiedosto kopioitu2 = kopioitava2.kopioi();
         int a;

         if (tietoTaiKansio != "") {
            //asetetaan kopioidulle tiedostolle uusi, haluttu nimi
            StringBuilder uusiNimi1 = new StringBuilder(tietoTaiKansio);
            kopioitu2.nimi(uusiNimi1);
         }
         //mikäli kansio ei sisällä kopioidun tiedoston kanssa samaa nimeä omaavaa
         //tiedostoa, voidaan asettaa kopioitu tiedosto nykyhakemistoon
         if (!nykyinen.sisalto().contains(kopioitu2)) {
            nykyinen.lisaa(kopioitu2);
            kopioidut++; 
         }         
      }
      //jos kopiointi ei onnistu, heitetään poikkeus
      if (kopioidut == 0) {
         throw new IllegalArgumentException("Error!");
      }
   }

   /**
    * Metodi, jolla on mahdollista suorittaa rekursiivinen listaus juurihakemistosta.
    * Listaa kaikki mahdolliset kansiot sekä tiedostot
    */
   public void rekursiivinenListaus() {
      /*
      //käytetään iteraattoria apuna
      //HakemistoIteraattori<Tieto> tulostus1 = juuri.iterator();
      HakemistoIteraattori<Tieto> tulostus1 = (HakemistoIteraattori)juuri.iterator();
      LinkedList<Tieto> apu = new LinkedList<>();
      //listataan rekursiivisesti nykyhakemistosta alkaen
      //käyttäen iteraattorin operaatioita
      LinkedList<Tieto> alkiot1 = juuri.esijarjestys(nykyinen, apu);
      tulostus1.alkiot(alkiot1);
      while(tulostus1.hasNext()) {
         System.out.println(tulostus1.next());
      }
      */
      HakemistoIteraattori<Tieto> tulostus1 = (HakemistoIteraattori)juuri.iterator();
      LinkedList<Tieto> apu = new LinkedList<>();
      LinkedList<Tieto> alkiot1 = juuri.esijarjestys(nykyinen, apu);
      
      while(tulostus1.hasNext()) {
         apu.add(tulostus1.next());
      }
      System.out.println(apu);
      apu = new LinkedList<Tieto>();
   }
   
   /**
    * Metodi, jolla poistutaan ohjelmasta
    * @return palauttaa aina false
    */
   public boolean poistu() {
      return false;
   }

   /**
    * Metodi, jolla pidetään kirjaa hakemistopolusta
    * @return palauttaa hakemistopolun merkkijonona
    */
   public String polku() {
      String polku = "";
      Hakemisto apu = nykyinen;

      while(apu != juuri) {
         polku = "/" + apu.nimi() + polku;
         apu = apu.ylihakemisto();
      }
      polku = polku + "/>";
      return polku;
   }
}