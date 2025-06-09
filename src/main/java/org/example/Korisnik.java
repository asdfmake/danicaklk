package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Korisnik {
    private String korisnickoIme;
    private boolean anoniman;
    private List<Preporuka> listaPreporuka = new ArrayList<Preporuka>();

    public boolean isAnoniman() {
        return anoniman;
    }

    public void setAnoniman(boolean anoniman) {
        this.anoniman = anoniman;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public Korisnik(String korisnickoIme, boolean anoniman) {
        this.korisnickoIme = korisnickoIme;
        this.anoniman = anoniman;
    }

    public List<Preporuka> getListaPreporuka() {
        return listaPreporuka;
    }

    public void preporuci(Film film, List<Korisnik> listaKorisnika){
        for(Korisnik korisnik: listaKorisnika){
            Preporuka preporuka = new Preporuka(this, korisnik, film);
            this.listaPreporuka.add(preporuka);
        }
    }

    public void dajMisljenje(Oceni ocenljivo, double ocena, String komentar){
        ocenljivo.ostaviRecenziju(ocena, komentar, this);
    }

    //Ova dva override-a su automatski generisana kada pritisnes na alt+insert i kliknes na toString() i na equals() and getHashCode()
    @Override
    public String toString() {
        return "Korisnik{" +
                "korisnickoIme='" + korisnickoIme + '\'' +
                ", anoniman=" + anoniman +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Korisnik korisnik = (Korisnik) o;
        return Objects.equals(korisnickoIme, korisnik.korisnickoIme);
    }

}
