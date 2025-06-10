package org.example;

import java.util.Map;

public class Preporuka implements Oceni {
    private Korisnik k1;
    private Korisnik k2;
    private Film film;
    private Map<String, Recenzija> mapaRecenzija;

    public Film getFilm() {
        return film;
    }

    public Preporuka(Korisnik k1, Korisnik k2, Film film) {
        this.k1 = k1;
        this.k2 = k2;
        this.film = film;
    }

    @Override
    public boolean ostaviRecenziju(Double ocena, String komentar, Korisnik korisnik) {
        if (!korisnik.getListaPreporuka().contains(this)) {
            return false;
        }

        if(ocena < 1 || ocena > 5){
            return false;
        }

        //ova dva mozes da kopiras iz Film klase
        Recenzija recenzija = new Recenzija(ocena, komentar);
        mapaRecenzija.put(korisnik.getKorisnickoIme(), recenzija);
        return true;
    }
}
