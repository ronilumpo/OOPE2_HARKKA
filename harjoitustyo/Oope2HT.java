package harjoitustyo;
import harjoitustyo.*;
/**
 * Oope2-harjoitustyön ajoluokka.
 * @param args
 * @author Roni Lumpo (roni.lumpo@tuni.fi)
 */
 
public class Oope2HT {

   public static void main(String[] args) {
      //luodaan tulkkiolio ja käyttöliittymäoliot
      //ja käynnistetään käyttöliittymäolion pääsilmukka
      Tulkki tulkki = new Tulkki();
      TUI kayttoliittyma = new TUI();
      kayttoliittyma.ajo();
   }

   
}