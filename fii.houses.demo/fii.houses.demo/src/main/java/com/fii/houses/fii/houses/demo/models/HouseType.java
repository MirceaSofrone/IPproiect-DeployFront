package com.fii.houses.fii.houses.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

@Entity
public class HouseType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID houseTypeID;
    private String type;
    private Date creationDate;

    public UUID getHouseTypeID() {
        return houseTypeID;
    }

    public void setHouseTypeID(UUID houseTypeID) {
        this.houseTypeID = houseTypeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
