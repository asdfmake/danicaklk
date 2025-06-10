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

        for(Film f : listaFilmova){
            double ukupno = 0;
            for(Recenzija r : f.getMapaRecenzija().values()){
                ukupno += r.getOcena();
            }
            //ovde imamo ukupnu ocenu za neki film

            if(reziserOcena.containsKey(f.getReziser())){
                ukupno += reziserOcena.get(f.getReziser());
                reziserOcena.put(f.getReziser(), ukupno);
            }
            else{
                reziserOcena.put(f.getReziser(), ukupno);
            }
            //iznad smo ubacili u mapu ime rezisera kao kljuc, a kao value je njegov ukupan score iz svih recenzija svakog filma
        }

        //ovde trebamo da sortiramo mapu
        List<Map.Entry<String, Double>> lista = new ArrayList<>(reziserOcena.entrySet());
        lista.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Return the director with the highest score (first in sorted list)
        if (!lista.isEmpty()) {
            return lista.get(0).getKey();
        } else {
            return null; // or "Nema podataka", depending on your design
        }

    }

    public List<Film> vratiPreporucene(Korisnik korisnik){
        List<Film> preporuceni = new ArrayList<>();

        for(Preporuka p : korisnik.getListaPreporuka()){
            preporuceni.add(p.getFilm());
        }

        return preporuceni;
    }
}
