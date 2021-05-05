package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.User;
import com.fii.houses.fii.houses.demo.repository.HouseRepository;
import com.fii.houses.fii.houses.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class UsersService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private HouseRepository houseRepository;

    public List<User> getAllUsers() {
        List<User> allUsers = repository.findAll();
        if (allUsers.size() > 0) {
            return allUsers;
        } else {
            return new ArrayList<>();
        }
    }

    public User getUserByUserID(UUID id){
        List<User> allUsers = repository.findAll();
        for (User existingUser : allUsers) {
            if (existingUser.getUserID().equals(id)) {
                return existingUser;
            }
        }
        return new User();
    }

    public User create(User user) {
        user.setUserID(UUID.randomUUID());
        user.setCreationDate(new Date());
        user=repository.save(user);
        return user;
    }

    public User update(User user){
        UUID id = user.getUserID();
        if(repository.existsById(id)){
            User newUser = repository.findById(id).get();
            if(user.getFirstName()!=null){
                newUser.setFirstName(user.getFirstName());
            }
            if(user.getLastName()!=null){
                newUser.setLastName(user.getLastName());
            }
            repository.save(newUser);
            return newUser;
        }else{
            return null;
        }
    }

    public boolean deleteUser(UUID userid){
        if(repository.existsById(userid)){
            repository.deleteById(userid);
            return true;
        }else {
            return false;
        }
    }

    public void addToFavorites(User user, House house){
        Date today = new Date();
        Map<Date,Integer> favoriteHistory = house.getFavoriteHistory();
        List<House> favorite = user.getFavorite();
        if(!favorite.contains(house)){
            favorite.add(house);
            user.setFavorite(favorite);
        }
        if(favoriteHistory.get(today)==null){
            favoriteHistory.put(today,1);
        }
        else{
            int numberOfFave=favoriteHistory.get(today);
            numberOfFave++;
            favoriteHistory.replace(today,numberOfFave);
        }
        house.setFavoriteHistory(favoriteHistory);
        house.setNoOfFave(house.getNoOfFave()+1);
        repository.save(user);
        houseRepository.save(house);
    }

    public User getUserFavorite(UUID userid){
        if(repository.existsById(userid)){
            return repository.getOne(userid);
        }else {
            return null;
        }
    }

    public void removeFromFavorites(User user,House house){
        List<House> favorite = user.getFavorite();
        favorite.remove(house);
        user.setFavorite(favorite);
        house.setNoOfFave(house.getNoOfFave()-1);
        repository.save(user);
        houseRepository.save(house);
    }

    public void addToViewsHistory(House house,UUID userID){
        User user = getUserByUserID(userID);
        Queue<House> viewsHistory = user.getViewsHistory();
        viewsHistory.add(house);
        user.setViewsHistory(viewsHistory);
        house.setViews(house.getViews()+1);
        Date today = new Date();
        Map<Date,Integer> viewsStatistics = house.getViewsHistory();

        if(viewsStatistics.get(today)==null){
            viewsStatistics.put(today,house.getViews()+1);
        }
        else{
            viewsStatistics.replace(today,house.getViews()+1);
        }
        house.setViewsHistory(viewsStatistics);
        house.setViews(house.getViews()+1);
        repository.save(user);
        houseRepository.save(house);
    }
}
