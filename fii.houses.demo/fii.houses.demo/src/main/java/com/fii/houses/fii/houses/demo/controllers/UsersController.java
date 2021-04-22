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
import java.util.List;



@RestController
@RequestMapping("api/v1/users")
public class UsersController {
    @Autowired
    private UsersService service;
    @Autowired
    private HouseService houseService;

    /*
        CREATE - POST
        READ - GET
        UPDATE - PUT
        DELETE - DELETE
    */

    @GetMapping ("/getusers")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = service.getAllUsers();
        return new ResponseEntity<>(users, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user)
    {
        User newUser=service.create(user);
        return new ResponseEntity<>(newUser, new HttpHeaders(),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        User newUser=service.update(user);
        if(newUser != null){
            return new ResponseEntity<>(newUser, new HttpHeaders(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/delete")
    public ResponseEntity<String> delete(@RequestBody User user)
    {
       if(service.deleteUser(user)){
           return new ResponseEntity<>("" , new HttpHeaders(),HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>( new HttpHeaders(),HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/getfavorite")
    public ResponseEntity<List<House>> getFavorite(@RequestBody User user){
        User newUser = service.getUserFavorite(user);
        if(newUser==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            List<House> favorite = newUser.getFavorite();
            return new ResponseEntity<>(favorite, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @PutMapping("/addtofavorite")
    public ResponseEntity<?> addToFavorite (@RequestBody House house){
        List<User> newUser = service.getUserByUserID(house.getUserID());
        List<House> newHouse = houseService.getHouseByHouseID(house);
        if(newUser.isEmpty() || newHouse.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.addToFavorites(newUser.get(0), newHouse.get(0));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/removefromfavorite")
    public ResponseEntity<?> removeFromFavorite(@RequestBody House house){
        List<User> newUser = service.getUserByUserID(house.getUserID());
        List<House> newHouse = houseService.getHouseByHouseID(house);
        if(newUser.isEmpty() || newHouse.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.removeFromFavorites(newUser.get(0), newHouse.get(0));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

