package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.HouseProperty;
import com.fii.houses.fii.houses.demo.service.HouseService;
import com.fii.houses.fii.houses.demo.service.UsersService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.*;


@RestController
@RequestMapping("api/v1")
public class HousesController {
    @Autowired
    private HouseService service;
    @Autowired
    private UsersService usersService;

    @GetMapping("/allhouses")
    public ResponseEntity<List<House>> getHouses(@RequestParam int page){
        List<House> houses = service.getAllHousesPage(page);
        if(houses.equals(new ArrayList<>())){
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/housedetails/{houseid}")
    public ResponseEntity<House> houseDetails(@PathVariable UUID houseid)
    {
        House newHouse = service.housedetails(houseid);
        if(newHouse!=null){
            return new ResponseEntity<>(newHouse,new HttpHeaders(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sellerhouses")
    public ResponseEntity<List<House>> getHouseByUserID(@RequestBody House house){
        List<House> existingHouses = service.getHouseByUserID(house);
        if(existingHouses.equals(new ArrayList<>())){
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(existingHouses, new HttpHeaders(),HttpStatus.OK);
        }
    }
    @GetMapping("/housebyhouseid")
    public ResponseEntity<House> getHouseByHouseID(@RequestBody House house){
        House existingHouse = service.getHouseByHouseID(house);
        if(existingHouse==null){
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else{
            usersService.addToViewsHistory(existingHouse,existingHouse.getUserID());
            return new ResponseEntity<>(existingHouse, new HttpHeaders(),HttpStatus.OK);
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

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public ResponseEntity<?> createHouse(@RequestBody House house) {
        House newHouse=service.createHouse(house);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        JSONObject houseJsonObject = new JSONObject();
        houseJsonObject.put("nr_camere", 4F);
        houseJsonObject.put("an_constructie", 2016F);
        houseJsonObject.put("suprafata",50F);
        houseJsonObject.put("tip_proprietate", "APT");
        houseJsonObject.put("suprafata_teren", 50F);
        houseJsonObject.put("zona","copou");
        HttpEntity<String> request =
                new HttpEntity<>(houseJsonObject.toString(),requestHeaders);

        ResponseEntity<HouseProperty> responseEntity = restTemplate.exchange(
                "https://prices-ias.herokuapp.com/price",
                HttpMethod.POST,
                request,
                HouseProperty.class
        );
        HouseProperty houseProperty = new HouseProperty();
        if(responseEntity.getStatusCode() == HttpStatus.OK){
            houseProperty = responseEntity.getBody();
            System.out.println("user response retrieved ");
            System.out.println(houseProperty);
        }
        return new ResponseEntity<>(houseProperty,new HttpHeaders(),HttpStatus.CREATED);
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


