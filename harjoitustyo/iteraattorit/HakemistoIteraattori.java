package harjoitustyo.iteraattorit;
import java.util.*;
import harjoitustyo.tiedot.*;

/**
 * Konkreettinen HakemistoIteraattori-luokka, jolla on mahdollista iteroida hakemisto läpi.
 * Tätä luokkaa käytetään Hakemisto-luokan ilmentymien rekursiiviseen listaamiseen
 * 
 */
public class HakemistoIteraattori<E> implements Iterator<E> {
   /*
    * Attribuutit
    */

   public LinkedList<E> alkiot;
   public int indeksi;

   /*
    * Rakentaja
    */

   public HakemistoIteraattori(LinkedList<E> tieto) {
      //LinkedList<E> lista = new LinkedList<>();
      //alkiot = lista;
      alkiot = tieto;
      indeksi = 0; 
   }

   /*
    * Aksessorit
    */
   
   /**
    * alkiot-attribuutin setteri
    * @param alkiot1 Linkitetty-lista, joka sisältää hakemiston alkiot
    */ 
   public void alkiot(LinkedList<E> alkiot1) {
      alkiot = alkiot1;
   }
    
   /*
    * Metodit
    */ 

   /**
    * Tarkistaa, onko alkiolla seuraavaa alkiota vai ei
    * @return totuusarvo, onko seuraavaa alkiota
    */ 
   @Override
   public boolean hasNext() {
      if (indeksi < alkiot.size()) {
         return true;
      }
      else {
         return false;
      }  
   }

   /**
    * Palauttaa seuraavan tietoalkion, mikäli hasNext() palauttaa true.
    * @return palauttaa tietoalkion, mikäli tietoalkio ei ole viimeinen
    */
   @Override
   public E next() throws NoSuchElementException {
      if (this.hasNext()) {
         E alkio = alkiot.get(indeksi);
         indeksi++;
         return alkio;
      }
      //jos alkiolla ei ole seuraavaa alkiota, heitetään poikkeus
      else {
         throw new NoSuchElementException();
      }
   }
} 