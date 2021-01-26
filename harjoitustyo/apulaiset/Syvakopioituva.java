package harjoitustyo.apulaiset;

/**
 * Tiedoston syv�kopiointiin soveltuva metodi. Kiinnit� geneerinen tyyppi T
 * tyypiksi Tiedosto, kun toteutat t�m�n rajapinnan Tiedosto-luokassa.
 * <p>
 * Harjoitusty�, Olio-ohjelmoinnin perusteet I, kev�t 2019.
 * <p>
 * @author Jorma Laurikkala (jorma.laurikkala@tuni.fi), Informaatioteknologian
 * ja viestinn�n tiedekunta, Tampereen yliopisto.
 *
 */

public interface Syvakopioituva<T> {
   /**
    * Syv�kopioi tiedoston ja palauttaa viitteen kopioon. Muista toteuttaa
    * Serializable-rajapinta Tieto- ja Tiedosto-luokissa, jos teet syv�kopioinnin
    * kurssilla esitellyll� menetelm�ll� (luentorungon luku 19). Serializable-
    * rajapinnan toteukseen riitt�� t�ss� ty�ss� pelkk� avain sanalla implements
    * tehty sopimus Tieto- ja Tiedosto-luokkien otsikossa. Serializable-rajapinnan
    * metodien toteutusta ei tarvita.
    *
    * @return viite syv�kopioituun tiedostoon.
    */
   abstract public T kopioi();
}
