package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/houses")
public class HousesController {
    @Autowired
    private HouseService service;

    @GetMapping
    public ResponseEntity<List<House>> getHouses(){
        List<House> houses=service.getAllHouses();
        return new ResponseEntity<List<House>>(houses,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<House> createOrUpdateHouse(@RequestBody House house)
    {
        House house1=service.createOrUpdate(house);
        return new ResponseEntity<House>(house1,new HttpHeaders(),HttpStatus.CREATED);
    }



}
