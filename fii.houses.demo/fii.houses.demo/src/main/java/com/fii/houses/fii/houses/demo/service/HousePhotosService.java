package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.HousePhotos;
import com.fii.houses.fii.houses.demo.repository.HousePhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HousePhotosService {
    @Autowired
    private HousePhotosRepository housePhotosRepository;

    public HousePhotos store(MultipartFile file, UUID houseID) throws IOException {
        HousePhotos housePhoto = new HousePhotos(UUID.randomUUID(), houseID, file.getBytes());
        housePhotosRepository.save(housePhoto);
        return  housePhoto;
    }

    public List<HousePhotos> getPhotosFromHouseID(UUID houseID) {
        List<HousePhotos> allHousesPhotos = housePhotosRepository.findAll();
        List<HousePhotos> housePhotos = new ArrayList<>();
        for(HousePhotos photo : allHousesPhotos){
            if(photo.getHouseID().equals(houseID)){
                housePhotos.add(photo);
            }
        }
        return housePhotos;
    }
}