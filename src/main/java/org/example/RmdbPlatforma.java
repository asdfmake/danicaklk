package org.example;

import java.io.File;
import java.util.*;

public class RmdbPlatforma {

    public static List<Korisnik> listaKorisnika;
    public static List<Film> listaFilmova;
    public RmdbPlatforma() {

    }

    public String najboljiReziser(){
        Map<String, Double> reziserOcena = new HashMap<String, Double>();

        for(Film film : listaFilmova){
            double ukupno = film.getUkupno();
            //ovde imamo ukupnu ocenu za neki film

            if(reziserOcena.containsKey(film.getReziser())){
                ukupno += reziserOcena.get(film.getReziser());
                reziserOcena.put(film.getReziser(), ukupno);
            }
            else{
                reziserOcena.put(film.getReziser(), ukupno);
            }
            //iznad smo ubacili u mapu ime rezisera kao kljuc, a kao value je njegov ukupan score iz svih recenzija svakog filma
        }

        //ovde trebamo da sortiramo mapu
        List<Map.Entry<String, Double>> lista = new ArrayList<>(reziserOcena.entrySet());
        lista.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Return the director with the highest score (first in sorted list)
        if (lista.isEmpty()) {
            return null; // or "Nema podataka"
        } else {
            return lista.get(0).getKey();
        }

    }

    public List<Film> vratiPreporucene(Korisnik korisnik){
        List<Film> preporuceni = new ArrayList<>();

        for(Preporuka preporuka : korisnik.getListaPreporuka()){
            preporuceni.add(preporuka.getFilm());
        }

        return preporuceni;
    }
}
