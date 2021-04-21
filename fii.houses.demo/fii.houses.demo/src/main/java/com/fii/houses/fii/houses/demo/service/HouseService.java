package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.User;
import com.fii.houses.fii.houses.demo.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Optional<House> getHouseById(UUID id){
        Optional<House> house = repository.findById(id);
        return house;
    }

    public List<House> getHouseByHouseID(House house){
        UUID id = house.getHouseID();
        List<House> houses = repository.findAll();
        List<House> goodHouses = new ArrayList<>();
        for (House house1 : houses) {
            if (house1.getHouseID().equals(id)) {
                goodHouses.add(house1);
            }
        }
        if(goodHouses.size()>=1){
            return goodHouses;
        }else {
            return new ArrayList<>();
        }
    }

    public List<House> getHouseByAdress(House house){
        List<House> goodHouses = new ArrayList<>();
        List<House> houses = repository.findAll();
        String adress = house.getAdress();
        for (House house1 : houses) {
            if (house1.getAdress().equals(adress)) {
                goodHouses.add(house1);
            }
        }
        if(goodHouses.size()>=1){
            return goodHouses;
        }else {
            return new ArrayList<>();
        }
    }

    public List<House> getHouseByUserID(House house){
        UUID id = house.getUserID();
        List<House> houses = repository.findAll();
        List<House> goodHouses = new ArrayList<>();
        for (House house1 : houses) {
            if (house1.getUserID().equals(id)) {
                goodHouses.add(house1);
            }
        }
        if(goodHouses.size()>=1){
            return goodHouses;
        }else {
            return new ArrayList<>();
        }
    }

    public String getHouseViews(House house){
        UUID id = house.getHouseID();
        if(repository.existsById(id)){
            House houseViwes = repository.getOne(id);
            return houseViwes.getViews().toString();
        }
        return null;
    }

    public boolean updateViews(UUID houseid){
        if(repository.existsById(houseid)){
            House house1 = repository.getOne(houseid);
            int views = house1.getViews();
            house1.setViews(views+1);
            repository.save(house1);
            return true;
        }
        return false;
    }

    public House createHouse(House house) {
        house.setHouseID(UUID.randomUUID());
        house.setCreationDate(new Date());
        house.setViews(0);
        house=repository.save(house);
        return house;
    }

    public House updateHouse(House house) {
        UUID id = house.getHouseID();
        if(repository.existsById(id)){
            House updateHouse = repository.getOne(id);
            house.setCreationDate(new Date());
            if(house.getAdress()!=null)
                updateHouse.setAdress(house.getAdress());
            if(house.getCity()!=null)
                updateHouse.setCity(house.getCity());
            if(house.getCountry()!=null)
                updateHouse.setCountry(house.getCountry());
            if(house.getEtaj()!=null)
                updateHouse.setEtaj(house.getEtaj());
            if(house.getNrBai()!=null)
                updateHouse.setNrBai(house.getNrBai());
            if(house.getNrCamere()!=null)
                updateHouse.setNrCamere(house.getNrCamere());
            if(house.getSuprafata()!=null)
                updateHouse.setSuprafata(house.getSuprafata());
            if(house.getHouseType()!=null){
                updateHouse.setHouseType(house.getHouseType());
            }
            if(house.getSellType()!=null){
                updateHouse.setSellType(house.getSellType());
            }
            if(house.getDescription()!=null) {
                updateHouse.setDescription(house.getDescription());
            }
            house=repository.save(updateHouse);
            return house;
        }else
            return null;
    }

    public boolean deleteHouse(House house) {
        UUID id = house.getHouseID();
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}


