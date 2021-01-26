package harjoitustyo.apulaiset;
/**
 * Luokkahierarkialle yhteiset metodit. Toteuta t�m� rajapinta Tieto-luokassa.
 * <p>
 * Harjoitusty�, Olio-ohjelmoinnin perusteet I, kev�t 2019.
 * <p>
 * @author Jorma Laurikkala (jorma.laurikkala@tuni.fi), Informaatioteknologian
 * ja viestinn�n tiedekunta, Tampereen yliopisto.
 *
 */

public interface Tietoinen {
   /**
    * Tutkii vastaako tiedon nimi parametrina annettua nime� tai ilmausta.
    * Ilmaus on muodostettu k�ytt�m�ll� yht� tai kahta jokerimerkki�. Jokerimerkki
    * voi olla ilmauksen alussa tai lopussa tai sek� alussa ja lopussa. Ilmauksen
    * alussa oleva jokerimerkki kohdistaa vertailun nimen loppuun. Lopussa oleva
    * jokerimerkki toimii p�invastoin. Ilmauksen alussa ja lopussa olevat
    * jokerimerkit kohdistavat vertailun nimen keskelle. Kaikkein laajin ilmaus
    * on jokerimerkki itsess��n, joka vastaa aina tiedon nime�. Jokerimerkeist� on
    * kerrottu tarkemmin 
    * <a href="https://coursepages.uta.fi/tiea2-1b/kevat-2019/harjoitustyo/">
    * tarkan teht�v�nannon</a> luvussa 3.6.
    *
    * @param hakusana nimi tai ilmaus, johon tiedon nime� verrataan.
    * @return true, jos tiedon nimi vastaa parametria ja false, jos tiedon nimi
    * ei vastaa parametria, parametrina saatu ilmaus on muodoltaan virheellinen
    * (esimerkiksi kolme t�hte�) tai parametri on null-arvoinen.
    */
   abstract public boolean equals(String hakusana);
}
