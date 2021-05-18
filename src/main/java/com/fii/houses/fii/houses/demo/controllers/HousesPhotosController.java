package com.fii.houses.fii.houses.demo.controllers;

import com.fii.houses.fii.houses.demo.models.HousePhotos;
import com.fii.houses.fii.houses.demo.service.HousePhotosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/housesPhotos")
@Api(value = "Houses Photos Controller", description = "Shows photos houses")
public class HousesPhotosController {
    @Autowired
    private HousePhotosService housePhotosService;

    @ApiOperation(value = "Add a given photo for a given house ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 304, message = "House not modified"),
                    @ApiResponse(code = 201, message = "Created")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<?> storePhoto(@RequestParam("file") MultipartFile file, @RequestParam("houseID") UUID houseID) throws IOException{

        if(housePhotosService.getPhotosFromHouseID(houseID).size() < HousePhotos.LIMIT){
            HousePhotos housePhoto = housePhotosService.store(file,houseID);
            return new ResponseEntity<>(housePhoto,new HttpHeaders(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Ati atins limita de " + HousePhotos.LIMIT + " poze",new HttpHeaders(), HttpStatus.NOT_MODIFIED);
        }

    }

    @ApiOperation(value = "Get photo from a given house")
    @GetMapping("/{houseID}")
    public List<HousePhotos> getPhotosFromHouseID(@PathVariable UUID houseID){
        return housePhotosService.getPhotosFromHouseID(houseID);
    }
}
