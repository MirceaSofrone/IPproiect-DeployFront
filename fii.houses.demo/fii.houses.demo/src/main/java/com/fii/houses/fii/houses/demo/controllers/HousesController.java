package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("api/v1/houses")
public class HousesController {
    @Autowired
    private HouseService service;

    @GetMapping("/allhouses")
    public ResponseEntity<List<House>> getHouses(){
        List<House> houses = service.getAllHouses();
        if(houses.equals(new ArrayList<>())){
            return new ResponseEntity<List<House>>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<House>>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/housebyuserid")
    public ResponseEntity<List<House>> getHouseByUserID(@RequestBody House house){
        List<House> house1 = service.getHouseByUserID(house);
        if(house1.equals(new ArrayList<>())){
            return new ResponseEntity<List<House>>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<House>>(house1, new HttpHeaders(),HttpStatus.OK);
        }
    }
    @GetMapping("/housebyhouseid")
    public ResponseEntity<List<House>> getHouseByHouseID(@RequestBody House house){
        List<House> house1 = service.getHouseByHouseID(house);
        if(house1.equals(new ArrayList<>())){
            return new ResponseEntity<List<House>>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<List<House>>(house1, new HttpHeaders(),HttpStatus.OK);
        }
    }

    @GetMapping("/housebyadress")
    public ResponseEntity<List<House>> getHouseByAdress(@RequestBody House house){
        List<House> house1 = service.getHouseByAdress(house);
        if(house1.equals(new ArrayList<>())){
            return new ResponseEntity<List<House>>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else
        {
            return new ResponseEntity<List<House>>(house1, new HttpHeaders(), HttpStatus.OK);
        }
    }
    @GetMapping("/houseviews")
    public ResponseEntity<String> getHouseViwes(@RequestBody House house){
        String views = service.getHouseViews(house);
        if(views==null) {
            return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>(views, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<House> createHouse(@RequestBody House house)
    {
        House house1=service.createHouse(house);
        return new ResponseEntity<House>(house1,new HttpHeaders(),HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<House> updateHouse(@RequestBody House house)
    {
        House house1=service.updateHouse(house);
        if(house1==null){
            return new ResponseEntity<House>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<House>(house1,new HttpHeaders(),HttpStatus.OK);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteHouse(@RequestBody House house)
    {
        if(service.deleteHouse(house)){
            return new ResponseEntity<String>("" ,new HttpHeaders(),HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("" ,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{houseid}")
    public ResponseEntity<String> views(@PathVariable UUID houseid)
    {
        if(service.updateViews(houseid)){
            return new ResponseEntity<String>("", new HttpHeaders(),HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lastnine")
    public ResponseEntity<List<House>> lastNineHouses (){
        List<House> houses = service.lastNineHouse();
        if(houses.equals(new ArrayList<>())){
            return new ResponseEntity<List<House>>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<House>>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }

    //the words will be separated trough "-"
    @GetMapping("/filter/bySearch/{words}")
    public ResponseEntity<List<House>> searchInAddressAndDescription(@PathVariable String words){
        List<House> allHouses = service.getAllHouses();
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
                if(house.getAdress() != null){
                    if(house.getAdress().contains(word)){
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
        //sort descendind by number of matches
        Map<Integer, List<House>> reverseSortedMap = new TreeMap<Integer, List<House>>(Collections.reverseOrder());

        reverseSortedMap.putAll(wordsMatched);

        List<House> houses = new ArrayList<>();
        for (Map.Entry<Integer, List<House>> entry :reverseSortedMap.entrySet()) {
            housesTemp = entry.getValue();
            houses.addAll(housesTemp);
        }
        return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/filter/byFields")
    public ResponseEntity <List<House>> searchInFields(@RequestParam(required=false) Integer houseType,
                                                       @RequestParam(required=false) Integer sellType,
                                                       @RequestParam(required=false) String city,
                                                       @RequestParam(required=false) String country,
                                                       @RequestParam(required=false) Integer nrCamere,
                                                       @RequestParam(required=false) Integer etaj,
                                                       @RequestParam(required=false) Integer suprafata,
                                                       @RequestParam(required=false) Integer nrBai){

        List<House> allHouses = service.getAllHouses();
        List<House> houses = new ArrayList<>();

        for(House house : allHouses){
            if((houseType == null || house.getHouseType().equals(houseType)) &&
                    (sellType == null || house.getSellType().equals(sellType)) &&
                    (city == null || house.getCity().equals(city)) &&
                    (country == null || house.getCountry().equals(country)) &&
                    (nrCamere == null || house.getNrCamere().equals(nrCamere)) &&
                    (etaj == null || house.getEtaj().equals(etaj)) &&
                    (suprafata == null || house.getSuprafata().equals(suprafata)) &&
                    (nrBai == null || house.getNrBai().equals(nrBai))) {
                houses.add(house);
            }
        }

        if(houses.isEmpty()){
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }
}


