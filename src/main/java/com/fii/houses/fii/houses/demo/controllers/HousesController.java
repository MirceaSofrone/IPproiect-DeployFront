package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.service.HouseService;
import com.fii.houses.fii.houses.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("api/v1")
public class HousesController {
    @Autowired
    private HouseService service;
    @Autowired
    private UsersService usersService;

    @GetMapping("/allhouses")
    public ResponseEntity<List<House>> getHouses(@RequestParam int page, @RequestParam int number){
        List<House> houses = service.getAllHousesPage(page,number);
        if(houses.equals(new ArrayList<>())){
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }

    //When accessing a house you'll need the house id and the user who wants to see the house
    @GetMapping("/housedetails")
    public ResponseEntity<House> houseDetails(@RequestBody House house) {
        House newHouse = service.housedetails(house.getHouseID());
        if(newHouse!=null){
            service.updateViews(newHouse.getHouseID());
            if(house.getUserID()!=null)
                usersService.addToViewsHistory(newHouse, house.getUserID());
            return new ResponseEntity<>(newHouse,new HttpHeaders(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/houseviews/{houseId}")
    public ResponseEntity<String> getHouseViews(@PathVariable UUID houseId){
        String views = service.getHouseViews(houseId);
        if(views==null) {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(views, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/sellerhouses/{userid}")
    public ResponseEntity<List<House>> getHouseByUserID(@PathVariable UUID userid){
        List<House> existingHouses = service.getHouseByUserID(userid);
        if(existingHouses.equals(new ArrayList<>())){
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(existingHouses, new HttpHeaders(),HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createHouse(@RequestBody House house) throws IOException {
        if(service.geoLocation(house.getAddress())==null)
            return new ResponseEntity<>("Wrong address!", new HttpHeaders(), HttpStatus.NOT_FOUND);
        house.setRecommendedPrice(service.getPriceFromAPI(house));
        House newHouse=service.createHouse(house);
        if(newHouse == null){
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(newHouse, new HttpHeaders(), HttpStatus.OK);
        }
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

    @DeleteMapping("/delete/{houseid}")
    public ResponseEntity<String> deleteHouse(@PathVariable UUID houseid)
    {
        if(service.deleteHouse(houseid)){
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
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
    public ResponseEntity <List<House>> searchInFields(@RequestBody House house){
        List<House> houses = service.searchByFields(house);

        if(houses.isEmpty()){
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/geoLocation")
    public ResponseEntity<Pair<Double, Double>> getLocations(@RequestParam String address){
        Pair<Double, Double> location=service.geoLocation(address);
        if(location.equals(null)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(location, HttpStatus.OK);
        }}
}


