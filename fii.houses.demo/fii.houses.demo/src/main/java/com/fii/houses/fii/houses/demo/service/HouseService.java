package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class HouseService {
    @Autowired
    private HouseRepository repository;

    public List<House> getAllHouses() {
        List<House> houses = repository.findAll();
        if (houses.size() > 0) {
            return houses;
        } else {
            return new ArrayList<>();
        }
    }

    public House createOrUpdate(House house) {
        house.setHouseID(UUID.randomUUID());
        house.setCreationDate(new Date());
        house=repository.save(house);
        return house;
    }
}
