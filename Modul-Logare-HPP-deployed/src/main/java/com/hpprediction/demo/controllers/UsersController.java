package com.hpprediction.demo.controllers;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.service.HouseService;
import com.hpprediction.demo.entity.User;
import com.hpprediction.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/users")
@Api(value = "Users Controller", description = "")
public class UsersController {
    @Autowired
    private UserService service;
    @Autowired
    private HouseService houseService;

    /*
        CREATE - POST
        READ - GET
        UPDATE - PUT
        DELETE - DELETE
    */

    @ApiOperation(value = "All users ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful")
            }
    )
    @GetMapping ()
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = service.getAllUsers();
        return new ResponseEntity<>(users, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Return a given user ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 404, message = "Not Found")
            }
    )
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserByUserID(@PathVariable long userid){
        Optional<User> existingUser = service.getUserByUserID(userid);
        return existingUser.map(user -> new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND));
    }

//    @ApiOperation(value = "Create a user ")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 201, message = "Created")
//            }
//    )
//    @PostMapping("/create")
//    public ResponseEntity<User> create(@RequestBody User user)
//    {
//        User newUser=service.create(user);
//        return new ResponseEntity<>(newUser, new HttpHeaders(),HttpStatus.CREATED);
//    }

    @ApiOperation(value = "Update a user")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 404, message = "Not Found")
            }
    )
    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        User newUser=service.update(user);
        if(newUser != null){
            return new ResponseEntity<>(newUser, new HttpHeaders(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete a given user ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 404, message = "Not Found")
            }
    )
    @DeleteMapping ("/delete/{userid}")
    public ResponseEntity<String> delete(@PathVariable long userid) {
       if(service.deleteUser(userid)){
           return new ResponseEntity<>(new HttpHeaders(),HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
       }
    }

    @ApiOperation(value = "Return user favourite houses")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 404, message = "Not Found")
            }
    )
    @GetMapping("/getfavorite/{userid}")
    public ResponseEntity<List<House>> getFavorite(@PathVariable long userid){
        User newUser = service.getUserFavorite(userid);
        if(newUser==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            List<House> favorite = newUser.getFavorite();
            return new ResponseEntity<>(favorite, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add a house to favourite ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 400, message = "Bad Request")
            }
    )
    @PutMapping("/addtofavorite")
    public ResponseEntity<String> addToFavorite (@RequestBody House house){
      Optional<User> newUser = service.getUserByUserID(house.getUserID());
        House newHouse = houseService.getHouseByHouseID(house);
        if(newUser.isPresent() && newHouse!=null){
            if(newUser.get().getFavorite().size()==User.FAVOURITE_LIST_CAPACITY)
                return new ResponseEntity<>("Oops...you have reached the maximum numbers of favorite houses. Please remove one before you can add another.",HttpStatus.BAD_REQUEST);
            else{
                service.addToFavorites(newUser.get(), newHouse);
                return new ResponseEntity<>(HttpStatus.OK);}
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Add a house to favourite for a user ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 400, message = "Bad request")
            }
    )
    @PutMapping("/addtofavorite/{userid}/{houseid}")
    public ResponseEntity<String> addToFavorite2 (@PathVariable long userid, @PathVariable UUID houseid){
       Optional<User> newUser = service.getUserByUserID(userid);
        House newHouse = houseService.getHouseByHouseID2(houseid);
        if(newUser.isPresent() && newHouse!=null){
            if(newUser.get().getFavorite().size()==User.FAVOURITE_LIST_CAPACITY)
                return new ResponseEntity<>("Oops...you have reached the maximum numbers of favorite houses. Please remove one before you can add another.",HttpStatus.BAD_REQUEST);
            else{
                service.addToFavorites(newUser.get(), newHouse);
                return new ResponseEntity<>(HttpStatus.OK);}
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Remove a house from favorite ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 400, message = "Bad request")
            }
    )
    @DeleteMapping("/removefromfavorite")
    public ResponseEntity<?> removeFromFavorite(@RequestBody House house){
        Optional<User> newUser = service.getUserByUserID(house.getUserID());
        House newHouse = houseService.getHouseByHouseID(house);
        if(!newUser.isPresent() || newHouse==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.removeFromFavorites(newUser.get(), newHouse);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Remove a house from favorite for a given user ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 400, message = "Bad request")
            }
    )
    @DeleteMapping("/removefromfavorite/{userid}/{houseid}")
    public ResponseEntity<?> removeFromFavorite2(@PathVariable long userid, @PathVariable UUID houseid){
        Optional<User> newUser = service.getUserByUserID(userid);
        House newHouse = houseService.getHouseByHouseID2(houseid);
        if(!newUser.isPresent() || newHouse==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            service.removeFromFavorites(newUser.get(), newHouse);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "History ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful"),
                    @ApiResponse(code = 400, message = "Bad request")
            }
    )
    @GetMapping("/history")
    public ResponseEntity<?> getViewsHistory(@RequestParam long userID){
        Optional<User> user = service.getUserByUserID(userID);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(user.get().getViewsHistory(),new HttpHeaders(),HttpStatus.OK);
        }
    }
}

