package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.User;
import com.fii.houses.fii.houses.demo.service.HouseService;
import com.fii.houses.fii.houses.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/users")
public class UsersController {
    @Autowired
    private UsersService service;

    @Autowired
    HouseService houseService;
    /*
        CREATE - POST
        READ - GET
        UPDATE - PUT
        DELETE - DELETE
    */

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = service.getAllUsers();
        return new ResponseEntity<List<User>>(users, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createOrUpdateUser(@RequestBody User user)
    {
        User user1=service.createOrUpdate(user);
        return new ResponseEntity<User>(user1, new HttpHeaders(),HttpStatus.CREATED);
    }

    //un get pentru a trimite o lista de favorite catre Dashboard
    @GetMapping
    public ResponseEntity<List<House>> getFavorite(@RequestBody User user){
        Optional<User> user1 = service.getUserById(user.getUserID());
        if(user1.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            List<House> favorite = user.getFavorite();
            return new ResponseEntity<>(favorite, new HttpHeaders(), HttpStatus.OK);
        }
    }

    //un put pentru a adauga la favorite o casa
    @PutMapping//or patch?
    public ResponseEntity<?> addToFavorite (@RequestBody User user, @RequestBody House house){
        Optional<User> user1 = service.getUserById(user.getUserID());
        Optional<House> house1 = houseService.getHouseById(house.getHouseID());
        if(user1.isEmpty() || house1.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.addToFavorites(user, house);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //un put pentru a elimina o casa de la favorite
    @PutMapping
    public ResponseEntity<?> removeFromFavorite(@RequestBody User user, @RequestBody House house){
        Optional<User> user1 = service.getUserById(user.getUserID());
        Optional<House> house1 = houseService.getHouseById(house.getHouseID());
        if(user1.isEmpty() || house1.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.removeFromFavorites(user, house);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

