package harjoitustyo.tiedot;
import harjoitustyo.apulaiset.Tietoinen;
import java.io.Serializable;

/**
 * Abstrakti Tieto-luokka, joka sisältää tiedoille 
 * yhteiset piirteet.Toteuttaa Tietoinen-, Comparable- ja
 * Serializable-rajapinnat.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * </p>
 * @author Roni Lumpo (roni.lumpo@tuni.fi) 
 */ 
public abstract class Tieto implements Tietoinen, Comparable<Tieto>, Serializable {
    
    /*
     * Attribuutit
     */ 

    /** Attribuutti, joka kertoo Tieto-luokan ilmentymän nimen */ 
    private StringBuilder nimi;

    /*
     * Rakentajat
     */ 

    public Tieto() {
        nimi = new StringBuilder("");
    }

    /**
     * Tieto-luokan parametrillinen rakentaja.
     * 
     * @param alkuNimi tiedon nimi
     * @throws IllegalArgumentException jos nimi ei ole välillä a-z, A-Z tai 0-9 poislukien merkit "_" ja ".", kunhan
     * nimi ei koostu ainoastaan merkeistä
     */
    public Tieto(StringBuilder alkuNimi) throws IllegalArgumentException {
        nimi(alkuNimi);
    }
     
    /*
     * Aksessorit
     */ 
    
    public StringBuilder nimi() {
        return nimi;
    }

    /**
     * Tieto-luokan nimi-attribuutin setteri.
     * 
     * @param uusiNimi uusi nimi
     * @throws IllegalArgumentException jos nimi ei ole välillä a-z, A-Z tai 0-9 poislukien merkit "_" ja ".", kunhan
     * nimi ei koostu ainoastaan pisteistä.
     */
    public void nimi(StringBuilder uusiNimi) throws IllegalArgumentException {
        if (uusiNimi != null) {
            //muutetaan StringBuilder String-tyyppiseksi
            String uusiNimi1 = uusiNimi.toString();
            //katsotaan, onko parametrina saatu nimi kelvollinen
            if (uusiNimi1.matches("^[a-zA-Z0-9._]+$") && !uusiNimi1.matches("^[.]+$")) {
                nimi = uusiNimi;
            }
            else {
                throw new IllegalArgumentException("Error!"); 
            }    
        }
        //heitetään IllegalArgumentException jos nimessä on jotain kiellettyjä merkkejä tai jos parametrina
        //saatu StringBuilder on null arvoinen
        else {
            throw new IllegalArgumentException("Error!");
        }
    }
     
    /*
     * Metodit
     */  

    /**
     * Object-luokan korvattu metodi Tieto-luokassa.
     * 
     * @return nimi-attribuutin arvo
     */
    @Override
    public String toString() {
        return nimi.toString();
    }

    /**
     * Korvattu Object-luokan equals metodi
     * @param o vertailun kohde on object-luokan olio, joka muunnetaan Tieto-muotoiseksi
     * @return totuusarvo mikäli tiedot samat
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Tietoinen) {
            Tieto haettava = (Tieto)o;
            String hakusana = haettava.nimi().toString();
            //tehdään tämän olion nimestä String-tyyppinen vertailua varten
            String nimi = this.nimi().toString();
            return nimi.equals(hakusana);
        }
        else {
            return false;
        } 
           
    }

    /**
     * Tietoinen-rajapinnan korvattu metodi Tieto-luokassa.
     * 
     * @param hakusana haettava tieto
     * @return totuusarvo true, jos esiintymiä on
     *         totuusarvo false, jos esiintymiä ei ole
     */
    @Override
    public boolean equals(String hakusana) {
        if (hakusana != null && hakusana.length() > 0) {
            //tehdään tämän olion nimestä String-tyyppinen vertailua varten
            String nimi = this.nimi().toString();
            boolean samat = false;

            //jos hakusanassa on jokerimerkki alussa ja lopussa
            if (hakusana.charAt(0) == '*' && hakusana.charAt(hakusana.length() - 1) == '*') {
                String haku = "";
                //jos hakusana koostuu ainoastaan kahdesta jokerimerkistä
                //palautetaan false
                if (hakusana.length() == 2) {
                    samat = false;
                }
                else {
                    //poistetaan jokerimerkit
                    for (int i = 1; i < hakusana.length() - 1; i++) {
                        haku = haku + hakusana.charAt(i);
                    }
                    //jos tämän tiedon nimi sisältää hakusanassa olleen kirjaimen 
                    //tai kirjaimet, palautetaan true
                    if (nimi.contains(haku)) {
                        samat = true;
                    }
                    else {
                        samat = false;
                    }
                }    
            }
            //jos hakusanan alussa on jokerimerkki
            else if (hakusana.charAt(0) == '*') {
                int a = 1;
                //lähdetään vertaamaan nimeä lopusta, jotta voidaan jättää tähtimerkki
                //huomioimatta.
                for (int i = hakusana.length() - 1; i > 0; i--) {

                    if (hakusana.charAt(i) == nimi.charAt(nimi.length() - a)) {
                        a++;
                        samat = true;
                    }
                    else {
                        return false;
                    }
                }
            }
            //jos hakusanan lopussa on jokerimerkki
            else if (hakusana.charAt(hakusana.length() - 1) == '*') {
                //verrataan hakusanassa esiintyviä kirjaimia tiedoston nimeen
                //mikäli kaikki kirjaimet löytyvät nimestä samassa järjestyksessä
                //palautetaan true
                for (int i = 0; i <= hakusana.length() - 2; i++) {
                    if (hakusana.charAt(i) == nimi.charAt(i)) {
                        samat = true;
                    }
                    else {
                        //samat = false;
                        //jos ei ole samat, lopetetaan silmukointi ja palautetaan false
                        //i = hakusana.length() - 2;
                        return false;
                    }
                }
            }
            //jos hakusanassa ei ole jokerimerkkejä
            else {
                //jos tiedoston nimi on sama kuin hakusana, palautetaan true
                if (nimi.equals(hakusana)) {
                    samat = true;
                }
            }    
            return samat;
        }    
        else {
            return false;
        }    
    }

    /**
     * Comparable-rajapinnan korvattu metodi Tieto-luokassa.
     * 
     * @param o Tieto johon verrataan
     * @return 1, jos olion nimi on pidempi kuin vertailtavan
     *        -1, jos olion nimi on lyhyempi kuin vertailtavan
     *         0, jos olion nimi on saman pituinen kuin vertailtavan
     */
    @Override
    public int compareTo(Tieto o) {
        //kutsutaan tietojen toString-operaatioita, jotka 
        //palauttavat nimet joita vertaillaan
        String tama = this.toString();
        String tuo = o.toString(); 
        int tulos = tama.compareTo(tuo);

        //pieni muokkaus alkuperäiseen compareTo-metodiin, kun se toteutetaan
        //Tieto-luokassa. Palautetaan vain 1, 0 tai -1 eikä pituuksien erotusta
        if (tulos > 0) {
            return 1;
        }
        else if (tulos < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }
} 