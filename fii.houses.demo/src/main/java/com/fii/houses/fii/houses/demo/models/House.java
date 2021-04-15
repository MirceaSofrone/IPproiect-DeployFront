package com.fii.houses.fii.houses.demo.models;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID houseID;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userID;
    private String adress, city, country, description;
    private Integer nrCamere, etaj, suprafata, nrBai;
    private Integer tipImobil,views;
    private Date creationDate;


    public UUID getHouseID() {
        return houseID;
    }

    public void setHouseID(UUID houseID) { this.houseID = houseID; }

    public UUID getUserID() { return userID; }

    public void setUserID(UUID userID) { this.userID = userID; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNrCamere() {
        return nrCamere;
    }

    public void setNrCamere(Integer nrCamere) {
        this.nrCamere = nrCamere;
    }

    public Integer getEtaj() {
        return etaj;
    }

    public void setEtaj(Integer etaj) {
        this.etaj = etaj;
    }

    public Integer getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(Integer suprafata) {
        this.suprafata = suprafata;
    }

    public Integer getNrBai() {
        return nrBai;
    }

    public void setNrBai(Integer nrBai) {
        this.nrBai = nrBai;
    }

    public Integer getTipImobil() {
        return tipImobil;
    }

    public void setTipImobil(Integer tipImobil) {
        this.tipImobil = tipImobil;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getViews() { return views; }

    public void setViews(Integer views) { this.views = views; }
}
