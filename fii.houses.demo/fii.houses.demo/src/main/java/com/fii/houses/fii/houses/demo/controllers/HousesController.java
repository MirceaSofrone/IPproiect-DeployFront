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
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/housebyuserid")
    public ResponseEntity<List<House>> getHouseByUserID(@RequestBody House house){
        List<House> existingHouses = service.getHouseByUserID(house);
        if(existingHouses.equals(new ArrayList<>())){
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(existingHouses, new HttpHeaders(),HttpStatus.OK);
        }
    }
    @GetMapping("/housebyhouseid")
    public ResponseEntity<List<House>> getHouseByHouseID(@RequestBody House house){
        List<House> existingHouses = service.getHouseByHouseID(house);
        if(existingHouses.equals(new ArrayList<>())){
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(existingHouses, new HttpHeaders(),HttpStatus.OK);
        }
    }

    @GetMapping("/housebyaddress")
    public ResponseEntity<List<House>> getHouseByAddress(@RequestBody House house){
        List<House> existingHouses = service.getHouseByAddress(house);
        if(existingHouses.equals(new ArrayList<>())){
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else
        {
            return new ResponseEntity<>(existingHouses, new HttpHeaders(), HttpStatus.OK);
        }
    }
    @GetMapping("/houseviews")
    public ResponseEntity<String> getHouseViews(@RequestBody House house){
        String views = service.getHouseViews(house);
        if(views==null) {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(views, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<House> createHouse(@RequestBody House house)
    {
        House newHouse=service.createHouse(house);
        return new ResponseEntity<>(newHouse,new HttpHeaders(),HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<House> updateHouse(@RequestBody House house)
    {
        House existingHouse=service.updateHouse(house);
        if(existingHouse==null){
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(existingHouse,new HttpHeaders(),HttpStatus.OK);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteHouse(@RequestBody House house)
    {
        if(service.deleteHouse(house)){
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{houseid}")
    public ResponseEntity<String> views(@PathVariable UUID houseid)
    {
        if(service.updateViews(houseid)){
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lastadded")
    public ResponseEntity<List<House>> lastAddedHouses(){
        List<House> houses = service.lastAddedHouses();
        if(houses.equals(new ArrayList<>())){
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }

    //the words will be separated through "-"
    @GetMapping("/filter/bysearch/{words}")
    public ResponseEntity<List<House>> searchInAddressAndDescription(@PathVariable String words){
        List<House> houses = service.searchByWords(words);
        if(houses.isEmpty()){
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/filter/byfields")
    public ResponseEntity <List<House>> searchInFields(@RequestParam(required=false) Integer houseType,
                                                       @RequestParam(required=false) Integer sellType,
                                                       @RequestParam(required=false) String city,
                                                       @RequestParam(required=false) String country,
                                                       @RequestParam(required=false) Integer noOfRooms,
                                                       @RequestParam(required=false) Integer floor,
                                                       @RequestParam(required=false) Integer surface,
                                                       @RequestParam(required=false) Integer noOfBathrooms){

        List<House> houses = service.searchByFields(houseType, sellType, city, country, noOfRooms, floor, surface, noOfBathrooms);

        if(houses.isEmpty()){
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }
}


