package com.fii.houses.fii.houses.demo.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class HousePhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID photoID;
    @Column(columnDefinition = "BINARY(16)")
    private UUID houseID;

    @Lob
    private byte[] data;

    public HousePhotos() {
    }

    public HousePhotos(UUID photoID, UUID houseID, byte[] data) {
        this.photoID = photoID;
        this.houseID = houseID;
        this.data = data;
    }

    public UUID getPhotoID() {
        return photoID;
    }

    public void setPhotoID(UUID photoID) {
        this.photoID = photoID;
    }

    public UUID getHouseID() {
        return houseID;
    }

    public void setHouseID(UUID houseID) {
        this.houseID = houseID;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
