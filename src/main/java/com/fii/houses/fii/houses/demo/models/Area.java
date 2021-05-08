package com.fii.houses.fii.houses.demo.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID areaID;
    private String area;
    private double latitudeMax;
    private double latitudeMin;
    private double longitudeMax;
    private double longitudeMin;

    public String getArea() {
        return area;
    }

    public double getLatitudeMax() {
        return latitudeMax;
    }

    public double getLatitudeMin() {
        return latitudeMin;
    }

    public double getLongitudeMax() {
        return longitudeMax;
    }

    public double getLongitudeMin() {
        return longitudeMin;
    }
}
