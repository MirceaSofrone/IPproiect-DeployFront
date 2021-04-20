package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.User;
import com.fii.houses.fii.houses.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@Service
public class UsersService {
    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        List<User> users = repository.findAll();
        if (users.size() > 0) {
            return users;
        } else {
            return new ArrayList<>();
        }
    }

    public Optional<User> getUserById(UUID id){
        Optional<User> user = repository.findById(id);
        return user;
    }

    public User createOrUpdate(User user) {
        user.setUserID(UUID.randomUUID());
        user.setCreationDate(new Date());
        user=repository.save(user);
        return user;
    }

    public void addToFavorites(User user, House house){
        Date today = new Date();
        Map<Date,Integer> istoricFavorite = house.getIstoricFavorite();
        List<House> favorite = user.getFavorite();
        if(!favorite.contains(house)){
            favorite.add(house);
            user.setFavorite(favorite);
        }
        if(istoricFavorite.get(today)==null){
            istoricFavorite.put(today,1);
        }
        else{
            int numberOfFave=istoricFavorite.get(today);
            numberOfFave++;
            istoricFavorite.replace(today,numberOfFave);
        }
        house.setIstoricFavorite(istoricFavorite);
        house.setNoOfFave(house.getNoOfFave()+1);
    }

    public void removeFromFavorites(User user,House house){
        List<House> favorite = user.getFavorite();
        favorite.remove(house);
        user.setFavorite(favorite);
        house.setNoOfFave(house.getNoOfFave()-1);
    }

    public void addToIstoricVizualizare(House house,User user){
        Queue<House> istoricVizionare = user.getIstoricVizionare();
        istoricVizionare.add(house);
        user.setIstoricVizionare(istoricVizionare);
        house.setViews(house.getViews()+1);
    }
}
