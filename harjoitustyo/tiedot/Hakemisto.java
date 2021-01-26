package harjoitustyo.tiedot;
import harjoitustyo.apulaiset.Sailova;
import harjoitustyo.omalista.OmaLista;
import java.util.*;
import harjoitustyo.iteraattorit.*;

/**
 * Konkreettinen Hakemisto-luokka, joka periytyy Tieto-luokasta.
 * Toteuttaa Sailova-rajapinnan.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * </p>
 * @author Roni Lumpo (roni.lumpo@tuni.fi) 
 */ 
public class Hakemisto extends Tieto implements Sailova<Tieto>, Iterable<Tieto> {
    
    /*
     * Attribuutit
     */ 

    /** Sisältö-attribuutti, joka listaa Hakemisto-olion sisältämät tiedostot ja alihakemistot */ 
    private OmaLista<Tieto> sisalto;

    /** Ylihakemisto-attribuutti, joka kertoo, mikä on nykyhakemiston ylihakemisto */
    private Hakemisto ylihakemisto;

    /*
     * Rakentajat
     */ 

    public Hakemisto() {
        OmaLista<Tieto> lista = new OmaLista<>();
        sisalto = lista;
        ylihakemisto = null;
    }

    /**
     * Hakemisto-luokan parametrillinen rakentaja
     * 
     * @param alkuNimi hakemiston nimi 
     * @param hakemisto viite ylihakemistoon
     * @throws IllegalArgumentException jos nimi ei ole välillä a-z, A-Z tai 0-9 poislukien merkit "_"
     * ja ".", kunhan
     * nimi ei koostu ainoastaan merkeistä
     */
    public Hakemisto(StringBuilder alkuNimi, Hakemisto hakemisto) throws IllegalArgumentException {
        //kuormitetaan yliluokan rakentajaa
        super(alkuNimi);
        //asetetaan ylihakemistoksi parametrina saatu hakemisto
        ylihakemisto = hakemisto;
        //alustetaan tyhjä OmaLista ja liitetään attribuuttiin viite
        OmaLista<Tieto> alkuSisalto = new OmaLista<>();
        sisalto = alkuSisalto;
    }
     
    /*
     * Aksessorit
     */ 
    
    public OmaLista<Tieto> sisalto() {
        return sisalto;
    }   

    /**
     * Hakemisto-luokan sisalto attribuutin setteri
     * 
     * @param uusiSisalto uusi sisalto
     * @throws IllegalArgumentException jos uusi sisalto on null-arvoinen
     */
    public void sisalto(OmaLista<Tieto> uusiSisalto) throws IllegalArgumentException {
        //jos parametri ei ole null, asetetaan se attribuutin arvoksi.
        if (uusiSisalto != null) {
            sisalto = uusiSisalto;
        }
        //jos parametri on null-arvoinen, heitetään poikkeus
        else {
            throw new IllegalArgumentException("Error!");
        }
    }

    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }

    /**
     * Hakemisto-luokan ylihakemisto-attribuutin setteri
     * 
     * @param ylla viite ylihakemistoon
     * @throws IllegalArgumentException jos viite ylihakemistoon on null-arvoinen
     */
    public void ylihakemisto(Hakemisto ylla) throws IllegalArgumentException {
        if (ylla != null) {
            ylihakemisto = ylla;
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
     * Kutsuu yliluokassa korvattua toString-metodia, lisää kenoviivan 
     * sekä alihakemistojen ja tiedostojen lukumäärän ja palauttaa nämä tiedot 
     * @return merkkijonoesitys
     */ 
    @Override
    public String toString() {
        //kutsutaan yliluokan toString-operaatiota ja 
        //lisätään sen perään Hakemisto-luokan oman attribuutin (sisalto) koko. 
        return super.toString() + "/ " + sisalto.size();
    }
    
    /**
     * Sailova-rajapinnan korvattu hae-metodi Hakemisto-luokassa
     * 
     * @param hakusana hakusana
     * @return LinkedList jossa löydetyt alkiot 
     */ 
    @Override
    public LinkedList<Tieto> hae(String hakusana) {
        //luodaan uusi linkitetty lista hakutuloksia varten
        LinkedList<Tieto> loydetyt = new LinkedList<Tieto>();
        boolean osuma = false;

        //käydään sisalto-attribuutin (omalistan) alkiot läpi 
        //ja verrataan niiden nimiä hakusanaan
        for (int i = 0; i < sisalto.size(); i++) {
            Tieto t = sisalto.get(i);
            osuma = t.equals(hakusana);
            //jos hakutulos löytyi, lisätään alkio aluksi luotuun
            //linkitettyyn listaan
            if (osuma == true) {
                loydetyt.add(t);
            }
        }
        return loydetyt;
    }

    /**
     * Sailova-rajapinnan korvattu lisaa-metodi Hakemisto-luokassa
     * 
     * @param lisattava lisattava alkio sisalto-attribuuttiin
     * @return totuusarvo true, jos lisays onnistui. false, jos ei
     */
    @Override
    public boolean lisaa(Tieto lisattava) {
        //jos lisättävä toteuttaa Comparable-rajapinnan ja on erisuuri
        //kuin null, voidaan mennä kokeilemaan lisäystä
        if (lisattava != null && lisattava instanceof Comparable) {
            for (Tieto i : this.sisalto()) {
                String a = lisattava.nimi().toString();
                String b = i.nimi().toString();
                if (a.equals(b)) {
                    return false;
                }
            }
            boolean lisays = false;
            //mikäli lisäys onnistuu, palautetaan true
            lisays = sisalto.lisaa(lisattava);
            return lisays;  
        }    
        else {
            return false;
        }
    }


    /**
     * Sailova-rajapinnan korvattu poista-metodi Hakemisto-luokassa
     * 
     * @param poistettava poistettava alkio sisalto-attribuutista
     * @return totuusarvo true mikäli poisto onnistui, false mikäli ei
     */
    @Override
    public boolean poista(Tieto poistettava) {
        //jos poistettava ei ole null, voidaan kokeilla poistamista
        if (poistettava != null) {
            boolean poisto = false;
            //OmaListan metodi palauttaa poistettujen alkioiden lukumäärän
            int poistetut = sisalto.poista(poistettava);
            //jos jotain poistettiin, palautetaan true
            if (poistetut > 0) {
                poisto = true;
            }
            //jos mitään ei poistettu, palautetaan false
            else {
                poisto = false;
            }
            return poisto;
        }
        else {
            return false;
        }
    }

    /**
     * Iterable-rajapinnan iterator-metodin toteutus Hakemisto-luokassa
     * @return uusi hakemistoiteraattori
     */
    @Override
    public Iterator<Tieto> iterator() {
        //siirretään juurihakemiston sisältö apulistaan, joka annetaan 
        //parametrina hakemistoiteraattorille
        /*
        LinkedList<Tieto> alkiot = new LinkedList<Tieto>();
        for(int i = 0; i < this.sisalto().size(); i++) {
            Tieto alkio = this.sisalto().get(i);
            alkiot.add(alkio);     
        }
        */
        LinkedList<Tieto> tiedot = new LinkedList<>();
        LinkedList<Tieto> alkiot = esijarjestys(this, tiedot);
        return new HakemistoIteraattori<Tieto>(alkiot);
    }

    /**
     * Listaa rekursiivisesti alkiot Linkitetylle-listalle
     * 
     * @param hakemisto aloitushakemisto listaukselle
     * @param tiedot Linkitetty-lista, johon tietoalkiot kootaan 
     * @return Linkitetty-lista, jossa tietoalkiot ovat listattuna oikeaan järjestykseen
     */
    public LinkedList<Tieto> esijarjestys(Hakemisto hakemisto, LinkedList<Tieto> tiedot) {
        //asetetaan apuviite hakemiston tiedot säilövään listaan
        int i = 0;
        while(i < hakemisto.sisalto().size()) {
            Tieto alkio = hakemisto.sisalto().get(i);
            tiedot.add(alkio);      
            if (alkio instanceof Hakemisto) {
               Hakemisto uusi = (Hakemisto)alkio;
               this.esijarjestys(uusi, tiedot);
            }
            i++;
        }
        return tiedot;
    }

    
}