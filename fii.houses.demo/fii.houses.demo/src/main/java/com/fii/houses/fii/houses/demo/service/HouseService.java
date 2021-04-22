package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

class SortByDate implements Comparator<House> {

    @Override
    public int compare(House house1, House house2) {
        return house1.getCreationDate().compareTo(house2.getCreationDate());
    }
}

@Service
public class HouseService {
    @Autowired
    private HouseRepository repository;
    private final static Integer carouselSize = 9;

    public List<House> getAllHouses() {
        List<House> allHouses = repository.findAll();
        if (allHouses.size() > 0) {
            return allHouses;
        } else {
            return new ArrayList<>();
        }
    }

    public List<House> getHouseByHouseID(House house){
        UUID id = house.getHouseID();
        List<House> allHouses = repository.findAll();
        List<House> housesById = new ArrayList<>();
        for (House existingHouse : allHouses) {
            if (existingHouse.getHouseID().equals(id)) {
                housesById.add(existingHouse);
            }
        }
        if(housesById.size()>0){
            return housesById;
        }else {
            return new ArrayList<>();
        }
    }

    public List<House> getHouseByAddress(House house){
        List<House> housesByAddress = new ArrayList<>();
        List<House> allHouses = repository.findAll();
        String address = house.getAddress();
        for (House existingHouse : allHouses) {
            if (existingHouse.getAddress().equals(address)) {
                housesByAddress.add(existingHouse);
            }
        }
        if(housesByAddress.size()>0){
            return housesByAddress;
        }else {
            return new ArrayList<>();
        }
    }

    public List<House> getHouseByUserID(House house){
        UUID id = house.getUserID();
        List<House> allHouses = repository.findAll();
        List<House> housesByUserId = new ArrayList<>();
        for (House existingHouse : allHouses) {
            if (existingHouse.getUserID().equals(id)) {
                housesByUserId.add(existingHouse);
            }
        }
        if(housesByUserId.size()>0){
            return housesByUserId;
        }else {
            return new ArrayList<>();
        }
    }

    public String getHouseViews(House house){
        UUID id = house.getHouseID();
        if(repository.existsById(id)){
            House houseViews = repository.getOne(id);
            return houseViews.getViews().toString();
        }
        return null;
    }

    public boolean updateViews(UUID houseId){
        if(repository.existsById(houseId)){
            House house = repository.getOne(houseId);
            int views = house.getViews();
            house.setViews(views+1);
            repository.save(house);
            return true;
        }
        return false;
    }

    public List<House> lastAddedHouses(){
        List<House> allHouses = repository.findAll();
        allHouses.sort(new SortByDate());
        List<House> lastAddedHouses = new ArrayList<>();
        int noOfHouses = Math.min(allHouses.size(), carouselSize);
        for (int index = 0; index < noOfHouses; index++) {
            lastAddedHouses.add(allHouses.get(index));
        }
        if (lastAddedHouses.size() > 0) {
            return lastAddedHouses;
        } else {
            return new ArrayList<>();
        }
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
            if(house.getAddress()!=null)
                updateHouse.setAddress(house.getAddress());
            if(house.getCity()!=null)
                updateHouse.setCity(house.getCity());
            if(house.getCountry()!=null)
                updateHouse.setCountry(house.getCountry());
            if(house.getFloor()!=null)
                updateHouse.setFloor(house.getFloor());
            if(house.getNoOfBathrooms()!=null)
                updateHouse.setNoOfBathrooms(house.getNoOfBathrooms());
            if(house.getNoOfRooms()!=null)
                updateHouse.setNoOfRooms(house.getNoOfRooms());
            if(house.getSurface()!=null)
                updateHouse.setSurface(house.getSurface());
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

    public List<House> searchByWords(String words){
        List<House> allHouses = this.getAllHouses();
        List<House> housesTemp;
        Map<Integer, List<House>> wordsMatched = new TreeMap<>();
        String[] arrOfWords = words.split("-", 0);
        Integer counterMatches;
        //populate the map wordsMarched: for a number of matches we have a list of houses
        for(House house : allHouses){
            counterMatches = 0;
            for(String word : arrOfWords){
                if(house.getDescription() != null){
                    if(house.getDescription().contains(word)){
                        counterMatches++;
                    }
                }
                if(house.getAddress() != null){
                    if(house.getAddress().contains(word)){
                        counterMatches++;
                    }
                }
            }
            if(counterMatches != 0){
                if(wordsMatched.containsKey(counterMatches)){
                    housesTemp = wordsMatched.get(counterMatches);
                }else{
                    housesTemp = new ArrayList<>();
                }
                housesTemp.add(house);
                wordsMatched.put(counterMatches, housesTemp);
            }
        }
        //sort descending by number of matches
        Map<Integer, List<House>> reverseSortedMap = new TreeMap<>(Collections.reverseOrder());

        reverseSortedMap.putAll(wordsMatched);

        List<House> houses = new ArrayList<>();
        for (Map.Entry<Integer, List<House>> entry :reverseSortedMap.entrySet()) {
            housesTemp = entry.getValue();
            houses.addAll(housesTemp);
        }
        return houses;
    }

    public List<House> searchByFields(Integer houseType,Integer sellType, String city,String country,
                                      Integer noOfRooms,Integer floor, Integer surface, Integer noOfBathrooms){

        List<House> allHouses = this.getAllHouses();
        List<House> filteredHouses = new ArrayList<>();

        for(House house : allHouses){
            if((houseType == null || house.getHouseType().equals(houseType)) &&
                    (sellType == null || house.getSellType().equals(sellType)) &&
                    (city == null || house.getCity().equals(city)) &&
                    (country == null || house.getCountry().equals(country)) &&
                    (noOfRooms == null || house.getNoOfRooms().equals(noOfRooms)) &&
                    (floor == null || house.getFloor().equals(floor)) &&
                    (surface == null || house.getSurface().equals(surface)) &&
                    (noOfBathrooms == null || house.getNoOfBathrooms().equals(noOfBathrooms))) {
                filteredHouses.add(house);
            }
        }
        return filteredHouses;
    }
}


