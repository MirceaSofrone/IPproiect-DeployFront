package com.fii.houses.fii.houses.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseProperty {
    private String tip_proprietate;
    private Integer nr_camere;
    private Float suprafata;
    private Float suprafata_teren;
    private Integer an_constructie;
    private String zona;
    private Float pret_vanzare;
    private Float pret_chirie;

    public String getTip_proprietate() {
        return tip_proprietate;
    }

    public void setTip_proprietate(String tip_proprietate) {
        this.tip_proprietate = tip_proprietate;
    }

    public Integer getNr_camere() {
        return nr_camere;
    }

    public void setNr_camere(Integer nr_camere) {
        this.nr_camere = nr_camere;
    }

    public Float getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(Float suprafata) {
        this.suprafata = suprafata;
    }

    public Float getSuprafata_teren() {
        return suprafata_teren;
    }

    public void setSuprafata_teren(Float suprafata_teren) {
        this.suprafata_teren = suprafata_teren;
    }

    public Integer getAn_constructie() {
        return an_constructie;
    }

    public void setAn_constructie(Integer an_constructie) {
        this.an_constructie = an_constructie;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Float getPret_vanzare() {
        return pret_vanzare;
    }

    public void setPret_vanzare(Float pret_vanzare) {
        this.pret_vanzare = pret_vanzare;
    }

    public Float getPret_chirie() {
        return pret_chirie;
    }

    public void setPret_chirie(Float pret_chirie) {
        this.pret_chirie = pret_chirie;
    }


}
