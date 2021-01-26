package harjoitustyo.apulaiset;

/**
 * Pakolliset listaoperaatiot m��rittelev� rajapinta. Toteuta OmaLista-luokassa.
 * <p>
 * Harjoitusty�, Olio-ohjelmoinnin perusteet I, kev�t 2019.
 * <p>
 * @author Jorma Laurikkala (jorma.laurikkala@tuni.fi), Informaatioteknologian
 * ja viestinn�n tiedekunta, Tampereen yliopisto.
 *
 */

public interface Ooperoiva<E> {
   /**
    * Listan alkioiden v�lille muodostuu kasvava suuruusj�rjestys, jos lis�ys
    * tehd��n vain t�ll� operaatiolla, koska uusi alkion lis�t��n listalle siten,
    * ett� alkio sijoittuu kaikkien itse��n pienempien tai yht� suurien alkioiden
    * j�lkeen ja ennen kaikkia itse��n suurempia alkioita. Alkioiden suuruusj�rjestys
    * selvitet��n Comparable-rajapinnan compareTo-metodilla.
    * <p>
    * Jos parametri liittyy esimerkiksi kokonaislukuun 2 ja listan tietoalkiot ovat
    * [ 0, 3 ], on listan sis�lt� lis�yksen j�lkeen [ 0, 2, 3 ], koska {@literal 0 < 2 < 3}.
    * <p>
    * K�yt� toteutuksessa SuppressWarnings-annotaatiota, jotta k��nt�j� ei valittaisi
    * vaarallisesta tyyppimuunnoksesta.
    *
    * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
    * toteuttaneen Comparable-rajapinnan.
    * @return true, jos lis�ys onnistui. False, jos lis�ys ep�onnistui, koska uutta
    * alkiota ei voitu vertailla. Vertailu ep�onnistuu, kun parametri on null-arvoinen
    * tai siihen liittyv�ll� oliolla ei ole vastoin oletuksia Comparable-rajapinnan
    * toteutusta.
    */
   @SuppressWarnings({"unchecked"})
   public abstract boolean lisaa(E uusi);

   /**
    * Poistaa listalta viitteet, jota liittyv�t tietoalkioon, johon parametrina
    * annettu viite liittyy. Tosin sanoen listalta poistetaan viitteet x = get(ind),
    * joille lauseke "poistettava == get(ind)" on totta. Listalta poistetaan yleens�
    * joko yksi tai ei yht��n alkiota. Useita alkioita poistetaan, kun parametri liittyy
    * tietoalkioon, jonka kaksi tai useampaa viitett� jakaa. Listalla on jaettuja
    * alkioita esimerkiksi, jos viite on lis�tty listalle monta kertaa. L�yd�t
    * operaation toimintaa havainnollistavia esimerkkej� Olio-ohjelmoinnin perusteet I -kurssin
    * <a href="https://coursepages.uta.fi/tiea2-1a/kevat-2019/tentit/">mallitentist�</a>.
    *
    * @param poistettava viite tietoalkioon.
    * @return listalta poistettujen viitteiden lukum��r�.
    */
   public abstract int poista(E poistettava);
}
