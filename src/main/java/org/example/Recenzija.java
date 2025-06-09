package org.example;

public class Recenzija {
    private double ocena;
    private String komentar;

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Recenzija(double ocena, String komentar) {
        this.ocena = ocena;
        this.komentar = komentar;
    }
}
