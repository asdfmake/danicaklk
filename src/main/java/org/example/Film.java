package org.example;

import java.util.Map;

public class Film implements Oceni {
    private String naziv;
    private String reziser;
    private KategorijaFilma kategorijaFilma;
    private Map<Korisnik, Recenzija> mapaRecenzija;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getReziser() {
        return reziser;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public Map<Korisnik, Recenzija> getMapaRecenzija() {
        return mapaRecenzija;
    }

    public Film(String naziv, String reziser) {
        this.naziv = naziv;
        this.reziser = reziser;
        this.kategorijaFilma = KategorijaFilma.NEOCENJENO;
        RmdbPlatforma.listaFilmova.add(this);
    }

    public boolean ostaviRecenziju(Double ocena, String komentar, Korisnik korisnik){
        if(ocena < 1 || ocena > 5){
            return false;
        }

        if(mapaRecenzija.containsKey(korisnik.getKorisnickoIme())){
            return false;
        }

        Recenzija recenzija = new Recenzija(ocena, komentar);
        //mozes da ostavis korisnickoIme kao kljuc jer ti, iako je anoniman smes da sacuvas informaciju ali ne smes da je das (ovde samo cuvas informaciju ne dajes je)
        mapaRecenzija.put(korisnik, recenzija);
        //NEMOJ DA ZABORAVIS DA POZOVES FUNKCIJU ZA AZURIRANJE, JER DZABE SI JE TI NAPRAVILA AKO JE NE KORISTIS !!!
        azurirajKategoriju();
        return true;
    }

    public void azurirajKategoriju(){
        double zbir = 0;
        for (Map.Entry<Korisnik, Recenzija> entry : mapaRecenzija.entrySet()) {
            Korisnik key = entry.getKey();
            Recenzija value = entry.getValue();
            if (!key.isAnoniman()) {
                zbir += value.getOcena();
            }
        }

        double prosek = zbir/mapaRecenzija.size();

        if(prosek>=0 && prosek<=1){
            this.kategorijaFilma= KategorijaFilma.NOIR_KATASTROFA;
        }
        else if(prosek>1 && prosek<=2){
            this.kategorijaFilma=KategorijaFilma.LOS_SCENARIO;
        }
        else if(prosek>2 && prosek<=3){
            this.kategorijaFilma=KategorijaFilma.ZLATNA_SREDINA;
        }
        else if(prosek>3 && prosek<=4){
            this.kategorijaFilma=KategorijaFilma.BLOCKBUSTER_HIT;
        }
        else if(prosek>4 && prosek<=5){
            this.kategorijaFilma=KategorijaFilma.MAJSTORSKO_DELO;
        }
    }



}
