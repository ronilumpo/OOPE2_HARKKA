import harjoitustyo.tiedot.*;
import harjoitustyo.iteraattorit.*;
import harjoitustyo.*;
import java.util.*;

public class Testi {
   public static void main(String[] args) {
      // Luodaan hakemistot. Alihakemistoista asetetaan viitteet ylihakemistoon
      // ja alihakemistot lisätään ylihakemistoon.
      Hakemisto ylihakemisto = new Hakemisto(new StringBuilder("pics"), null);
      Hakemisto alihakemisto1 = new Hakemisto(new StringBuilder("cats"), ylihakemisto);
      Hakemisto alihakemisto2 = new Hakemisto(new StringBuilder("dogs"), ylihakemisto);
      ylihakemisto.lisaa(alihakemisto1);
      ylihakemisto.lisaa(alihakemisto2);

      // Lisätään tiedostoja alihakemistoihin.
      Tiedosto tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
      Tiedosto tiedosto2 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
      Tiedosto tiedosto3 = new Tiedosto(new StringBuilder("ceiling_cat.gif"), 3677);
      Tiedosto tiedosto4 = new Tiedosto(new StringBuilder("dangerous_kitten.jpg"), 13432);
      Tiedosto tiedosto5 = new Tiedosto(new StringBuilder("worlds_ugliest_dog.jpg"), 118088);
      alihakemisto1.lisaa(tiedosto1);
      alihakemisto1.lisaa(tiedosto2);
      alihakemisto1.lisaa(tiedosto3);
      alihakemisto1.lisaa(tiedosto4);
      alihakemisto2.lisaa(tiedosto5);

      // Kuljetaan ylihakemiston ja sen alihakemistojen läpi iteraattorilla ja tallennetaan iteraattorin
      // palauttamat viitteet taulukkoon.
      int lkm = ylihakemisto.sisalto().size() + alihakemisto1.sisalto().size() + alihakemisto2.sisalto().size();
      Tieto[] viitteet = new Tieto[lkm];
      Iterator<Tieto> iteraattori = ylihakemisto.iterator();
      int ind = 0;
      while (iteraattori.hasNext()) {
         viitteet[ind++] = iteraattori.next();
      }
      for (Tieto i : viitteet) {
         System.out.println(i);
      }
   }
}