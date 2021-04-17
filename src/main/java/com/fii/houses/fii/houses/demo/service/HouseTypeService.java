package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.HouseType;
import com.fii.houses.fii.houses.demo.repository.HouseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class HouseTypeService {
    @Autowired
    private HouseTypeRepository repository;

    public List<HouseType> getAllHouseTypes() {
        List<HouseType> houseTypes = repository.findAll();
        if (houseTypes.size() > 0) {
            return houseTypes;
        } else {
            return new ArrayList<>();
        }
    }

    public HouseType createOrUpdate(HouseType houseType) {
        houseType.setHouseTypeID(UUID.randomUUID());
        houseType.setCreationDate(new Date());
        houseType=repository.save(houseType);
        return houseType;
    }
}
