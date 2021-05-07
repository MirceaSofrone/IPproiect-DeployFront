package com.fii.houses.fii.houses.demo.controllers;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.HouseProperty;
import com.fii.houses.fii.houses.demo.service.HouseService;
import com.fii.houses.fii.houses.demo.service.UsersService;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;


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

    //When accessing a house you'll need the house id and the user who wants to see the house
    @GetMapping("/housedetails")
    public ResponseEntity<House> houseDetails(@RequestBody House house) {
        House newHouse = service.housedetails(house.getHouseID());
        if(newHouse!=null){
            service.updateViews(newHouse.getHouseID());
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

   /* @Autowired
    private RestTemplate restTemplate;*/

    @PostMapping("/create2")
    public ResponseEntity<House> createHouse2(@RequestBody House house) {
        House newHouse = service.createHouse(house);
        if(newHouse == null){
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(newHouse, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createHouse(@RequestBody House house) {
        //House newHouse=service.createHouse(house);

        JSONObject houseJsonObject = new JSONObject();
        houseJsonObject.put("nr_camere", 4);
        houseJsonObject.put("an_constructie", 2016);
        houseJsonObject.put("suprafata",50F);
        houseJsonObject.put("tip_proprietate", "APT");
        houseJsonObject.put("suprafata_teren", 50F);
        houseJsonObject.put("zona","copou");

        HouseProperty houseProperty1 = new HouseProperty();
        houseProperty1.setNr_camere(4);
        houseProperty1.setAn_constructie(2016);
        houseProperty1.setSuprafata(50F);
        houseProperty1.setTip_proprietate("APT");
        houseProperty1.setSuprafata_teren(50F);
        houseProperty1.setZona("copou");

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        //requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        /*HttpEntity<String> entityCredentials = new HttpEntity<String>(houseJsonObject.toString(), requestHeaders);

        restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        HttpEntity<JSONObject> request = new HttpEntity<>(houseJsonObject,requestHeaders);*/

       /* ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(
                "https://price-is.herokuapp.com/price",
                HttpMethod.GET,
                request,
                JSONObject.class
        );*/

        /*ResponseEntity<JSONObject> responseEntity =
                restTemplate.exchange(
                        "https://price-is.herokuapp.com/price" + 1L,
                        HttpMethod.GET,
                        new HttpEntity<>(requestHeaders),
                        JSONObject.class);
*/

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        HttpEntity<JSONObject> request = new HttpEntity<>(houseJsonObject, requestHeaders);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange("https://price-is.herokuapp.com/price",
                HttpMethod.GET, request, JSONObject.class);

        System.out.println("DAAA AM TRECUT");
        JSONObject houseProperty = new JSONObject();
        if(responseEntity.getStatusCode() == HttpStatus.OK){
            houseProperty = responseEntity.getBody();
            System.out.println("user response retrieved ");
        }else {
            System.out.println("NOT NOT NOT user response retrieved ");
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

    @PostMapping("/delete/{houseid}")
    public ResponseEntity<String> deleteHouse(@PathVariable UUID houseid)
    {
        if(service.deleteHouse(houseid)){
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.OK);
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
    public ResponseEntity <List<House>> searchInFields(House house){
        List<House> houses = service.searchByFields(house);

        if(houses.isEmpty()){
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(houses, new HttpHeaders(), HttpStatus.OK);
        }
    }
}


