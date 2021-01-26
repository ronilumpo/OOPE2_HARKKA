package harjoitustyo;
import harjoitustyo.apulaiset.In;
import harjoitustyo.tiedot.Tieto;
import java.util.*;

/**
 * Konkreettinen käyttöliittymä-luokka, joka lukee käyttäjältä tietoja ja
 * antaa tiedot eteenpäin Tulkki-luokalle.
 * @author Roni Lumpo (roni.lumpo@tuni.fi)
 */
public class TUI {
   /*
    * Attribuutit
    */

   /** Attribuutti, joka sisältää komennot yksiulotteisessa taulukossa */ 
    public String[] komennot;

   /** Attribuutti, josta luodaan tulkki */ 
    public Tulkki tulkki;

   /*
    * Rakentajat
    */ 

   public TUI() {
      Tulkki ajohomma = new Tulkki();
      tulkki = ajohomma;
   }
    
   /*
    * Aksessorit
    */ 

   public String[] komennot() {
      return komennot;
   }

   /*
    * Metodit
    */ 

   /**
    * Metodi, joka sisältää pääsilmukan ja jossa luetaan käyttäjältä syötteet
    * ja annetaan ne käsiteltäväksi tulkki-luokassa, tulostaa 
    * käyttäjän nähtäväksi prosessoinnin tulokset
    * @return jatketaan totuusarvo
    * @throws IllegalArgumentException jos tiedoston koko ei koostu pelkästää numeroista
    */ 
   public boolean ajo() throws IllegalArgumentException {

      /** Vakioitu merkki */
      final String VALI = "/>";

      boolean jatketaan = true;
      String[] komennot = new String[9];
      komennot[0] = "md";
      komennot[1] = "cd";
      komennot[2] = "mf";
      komennot[3] = "mv";
      komennot[4] = "ls";
      komennot[5] = "rm";
      komennot[6] = "cp";
      komennot[7] = "find";
      komennot[8] = "exit";

      System.out.println("Welcome to SOS.");
      //käyttöliittymän pääsilmukka
      while (jatketaan) {
         try {
            boolean osuiko = false;
            if (tulkki.nykyinen() == tulkki.juuri()) {
               System.out.print(VALI);
            }
            else {
               System.out.print(tulkki.polku());
            }
            //luetaan komennot käyttäjältä käyttäen In-luokkaa
            String komento = In.readString();
            String[] komentoOsat = komento.split(" ");
            String komentoLyhenne = komentoOsat[0];
            
            //silmukoidaan läpi tunnetut käskyt ja katsotaan täsmäävätkö
            //parametrit, mikäli komento on oikein
            for (int i = 0; i < 9; i++) {
               if (komentoLyhenne.equals(komennot[i])) {

                  //jos komento on "md"
                  if (i == 0 && komentoOsat.length == 2) {
                     osuiko = true;
                     tulkki.luoHakemisto(komentoOsat[1]);
                  } 
                  //jos komento on "cd"
                  if (i == 1 && (komentoOsat.length == 2 || komentoOsat.length == 1)) {
                     osuiko = true;
                     if (komentoOsat.length == 2) {
                        tulkki.hakemistoVaihto(komentoOsat[1]);
                     }
                     else {
                        tulkki.hakemistoVaihto("");
                     }
                  }
                  //jos komento on "mf"
                  if (i == 2 && komentoOsat.length == 3) {
                     osuiko = true;
                     if (komentoOsat[2].matches("^[0-9]+$")) {
                        int koko = Integer.parseInt(komentoOsat[2]);
                        tulkki.luoTiedosto(komentoOsat[1], koko);
                     }
                     else {
                        throw new IllegalArgumentException("Error!");
                     }     
                  }
                  //jo komento on "mv" 
                  if (i == 3 && komentoOsat.length == 3) {
                     osuiko = true;
                     tulkki.nimeaUudelleen(komentoOsat[1], komentoOsat[2]);
                  }
                  //jos komento on "ls"
                  if (i == 4 && (komentoOsat.length == 2 || komentoOsat.length == 1)) {
                     osuiko = true;
                     if (komentoOsat.length == 1) {
                        LinkedList<Tieto> alkiot = tulkki.listaa("*");
                        for(Tieto a : alkiot) {
                           System.out.println(a);
                        }
                     }
                     else {
                        LinkedList<Tieto> alkiot1 = tulkki.listaa(komentoOsat[1]);
                        for(Tieto s : alkiot1) {
                           System.out.println(s);
                        }
                     }   
                  }
                  //jos komento on "rm"
                  if (i == 5 && komentoOsat.length == 2) {
                     osuiko = true;
                     tulkki.poista(komentoOsat[1]);
                  }
                  //jos komento on "cp"
                  if (i == 6 && komentoOsat.length == 3) {
                     osuiko = true;
                        tulkki.kopioi(komentoOsat[1], komentoOsat[2]);
                  }
                  //jos komento on "find"
                  if (i == 7 && komentoOsat.length == 1) {
                     osuiko = true;
                     tulkki.rekursiivinenListaus();
                  }
                  //jos komento on "exit"
                  if (i == 8 && komentoOsat.length == 1) {
                     osuiko = true;
                     jatketaan = false;
                  }
               }
            }
            //jos komento ei vastaa tunnettuja komentoja tai
            //parametreja on väärä määrä, tulostetaan virheilmoitus
            if (osuiko == false) {
               System.out.println("Error!");
            }
         }
         //napataan IllegalArgumentException ja tulostetaan Error! -teksti
         catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
         }
         //jos jotain muuta menee pieleen.
         catch(Exception a) {
            System.out.println("Error!");
            a.getStackTrace();
         }   

      }
      //kun lopetetaan ohjelman silmukointi, tulostetaan poistumisilmoitus
      System.out.println("Shell terminated.");
      return jatketaan;
   } 
} 