package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.HouseType;
import com.fii.houses.fii.houses.demo.service.HouseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/houseTypes")
public class HouseTypesController {
    @Autowired
    private HouseTypeService service;

    @GetMapping
    public ResponseEntity<List<HouseType>> getHousesType(){
        List<HouseType> houseTypes =service.getAllHouseTypes();
        return new ResponseEntity<List<HouseType>>(houseTypes,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HouseType> createOrUpdateHouseType(@RequestBody HouseType houseType)
    {
        HouseType houseType1 = service.createOrUpdate(houseType);
        return new ResponseEntity<HouseType>(houseType1,new HttpHeaders(),HttpStatus.CREATED);
    }
}
