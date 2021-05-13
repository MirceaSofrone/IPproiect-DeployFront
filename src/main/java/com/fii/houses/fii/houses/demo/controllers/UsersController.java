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
import java.util.UUID;


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
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserByUserID(@PathVariable UUID userid){
        User existingUser = service.getUserByUserID(userid);
        if(existingUser==null){
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(existingUser, new HttpHeaders(),HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user)
    {
        User newUser=service.create(user);
        return new ResponseEntity<>(newUser, new HttpHeaders(),HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        User newUser=service.update(user);
        if(newUser != null){
            return new ResponseEntity<>(newUser, new HttpHeaders(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/delete/{userid}")
    public ResponseEntity<String> delete(@PathVariable UUID userid)
    {
       if(service.deleteUser(userid)){
           return new ResponseEntity<>(new HttpHeaders(),HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/getfavorite/{userid}")
    public ResponseEntity<List<House>> getFavorite(@PathVariable UUID userid){
        User newUser = service.getUserFavorite(userid);
        if(newUser==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            List<House> favorite = newUser.getFavorite();
            return new ResponseEntity<>(favorite, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @PutMapping("/addtofavorite")
    public ResponseEntity<String> addToFavorite (@RequestBody House house){
        User newUser = service.getUserByUserID(house.getUserID());
        House newHouse = houseService.getHouseByHouseID(house);
        if(newUser!=null && newHouse!=null){
            if(newUser.getFavorite().size()==User.FAVOURITE_LIST_CAPACITY)
                return new ResponseEntity<>("Oops...you have reached the maximum numbers of favorite houses. Please remove one before you can add another.",HttpStatus.BAD_REQUEST);
            else{
                service.addToFavorites(newUser, newHouse);
                return new ResponseEntity<>(HttpStatus.OK);}
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addtofavorite/{userid}/{houseid}")
    public ResponseEntity<String> addToFavorite2 (@PathVariable UUID userid, @PathVariable UUID houseid){
        User newUser = service.getUserByUserID(userid);
        House newHouse = houseService.getHouseByHouseID2(houseid);
        if(newUser!=null && newHouse!=null){
            if(newUser.getFavorite().size()==User.FAVOURITE_LIST_CAPACITY)
                return new ResponseEntity<>("Oops...you have reached the maximum numbers of favorite houses. Please remove one before you can add another.",HttpStatus.BAD_REQUEST);
            else{
                service.addToFavorites(newUser, newHouse);
                return new ResponseEntity<>(HttpStatus.OK);}
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removefromfavorite")
    public ResponseEntity<?> removeFromFavorite(@RequestBody House house){
        User newUser = service.getUserByUserID(house.getUserID());
        House newHouse = houseService.getHouseByHouseID(house);
        if(newUser==null || newHouse==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.removeFromFavorites(newUser, newHouse);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/removefromfavorite/{userid}/{houseid}")
    public ResponseEntity<?> removeFromFavorite2(@PathVariable UUID userid, @PathVariable UUID houseid){
        User newUser = service.getUserByUserID(userid);
        House newHouse = houseService.getHouseByHouseID2(houseid);
        if(newUser==null || newHouse==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.removeFromFavorites(newUser, newHouse);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getViewsHistory(@RequestParam UUID userID){
        User user = service.getUserByUserID(userID);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(user.getViewsHistory(),new HttpHeaders(),HttpStatus.OK);
        }
    }
}

