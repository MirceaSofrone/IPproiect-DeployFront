package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.UserActivity;
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
    public ResponseEntity<List<UserActivity>> getUsers(){
        List<UserActivity> userActivities = service.getAllUsers();
        return new ResponseEntity<>(userActivities, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/{userid}")
    public ResponseEntity<UserActivity> getUserByUserID(@PathVariable UUID userid){
        UserActivity existingUserActivity = service.getUserByUserID(userid);
        if(existingUserActivity ==null){
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(existingUserActivity, new HttpHeaders(),HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserActivity> create(@RequestBody UserActivity userActivity)
    {
        UserActivity newUserActivity = service.create(userActivity);
        return new ResponseEntity<>(newUserActivity, new HttpHeaders(),HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UserActivity> update(@RequestBody UserActivity userActivity){
        UserActivity newUserActivity =service.update(userActivity);
        if(newUserActivity != null){
            return new ResponseEntity<>(newUserActivity, new HttpHeaders(), HttpStatus.OK);
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
        UserActivity newUserActivity = service.getUserFavorite(userid);
        if(newUserActivity ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            List<House> favorite = newUserActivity.getFavorite();
            return new ResponseEntity<>(favorite, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @PutMapping("/addtofavorite")
    public ResponseEntity<String> addToFavorite (@RequestBody House house){
        UserActivity newUserActivity = service.getUserByUserID(house.getUserID());
        House newHouse = houseService.getHouseByHouseID(house);
        if(newUserActivity !=null && newHouse!=null){
            if(newUserActivity.getFavorite().size()== UserActivity.FAVOURITELISTCAPACITY)
                return new ResponseEntity<>("Oops...you have reached the maximum numbers of favorite houses. Please remove one before you can add another.",HttpStatus.BAD_REQUEST);
            else{
                service.addToFavorites(newUserActivity, newHouse);
                return new ResponseEntity<>(HttpStatus.OK);}
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addtofavorite/{userid}/{houseid}")
    public ResponseEntity<String> addToFavorite2 (@PathVariable UUID userid, @PathVariable UUID houseid){
        UserActivity newUserActivity = service.getUserByUserID(userid);
        House newHouse = houseService.getHouseByHouseID2(houseid);
        if(newUserActivity !=null && newHouse!=null){
            if(newUserActivity.getFavorite().size()== UserActivity.FAVOURITELISTCAPACITY)
                return new ResponseEntity<>("Oops...you have reached the maximum numbers of favorite houses. Please remove one before you can add another.",HttpStatus.BAD_REQUEST);
            else{
                service.addToFavorites(newUserActivity, newHouse);
                return new ResponseEntity<>(HttpStatus.OK);}
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removefromfavorite")
    public ResponseEntity<?> removeFromFavorite(@RequestBody House house){
        UserActivity newUserActivity = service.getUserByUserID(house.getUserID());
        House newHouse = houseService.getHouseByHouseID(house);
        if(newUserActivity ==null || newHouse==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.removeFromFavorites(newUserActivity, newHouse);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/removefromfavorite/{userid}/{houseid}")
    public ResponseEntity<?> removeFromFavorite2(@PathVariable UUID userid, @PathVariable UUID houseid){
        UserActivity newUserActivity = service.getUserByUserID(userid);
        House newHouse = houseService.getHouseByHouseID2(houseid);
        if(newUserActivity ==null || newHouse==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.removeFromFavorites(newUserActivity, newHouse);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getViewsHistory(@RequestParam UUID userID){
        UserActivity userActivity = service.getUserByUserID(userID);
        if(userActivity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(userActivity.getViewsHistory(),new HttpHeaders(),HttpStatus.OK);
        }
    }
}

