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
        if (!allUsers.isEmpty()) {
            return allUsers;
        } else {
            return new ArrayList<>();
        }
    }

    public Optional<User> getUserByUserID(UUID id) {
        return  repository.findById(id);
    }

    public User create(User user) {
        user.setUserID(UUID.randomUUID());
        user.setCreationDate(new Date());
        user = repository.save(user);
        return user;
    }

    public User update(User user) {
        UUID id = user.getUserID();
        Optional<User>availableUser=repository.findById(id);
        User newUser;
        if (availableUser.isPresent()) {
            newUser = availableUser.get();
            if (user.getFirstName() != null) {
                newUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                newUser.setLastName(user.getLastName());
            }
            if(user.getPhoneNumber() != null){
                newUser.setPhoneNumber(user.getPhoneNumber());
            }
            repository.save(newUser);
            return newUser;
        } else {
            return null;
        }
    }

    public boolean deleteUser(UUID userid) {
        if (repository.existsById(userid)) {
            repository.deleteById(userid);
        }
        return repository.existsById(userid);
    }

    public void addToFavorites(User user, House house) {
        Date today = new Date();
        Map<Date, Integer> favoriteHistory = house.getFavoriteHistory();
        List<House> favorite = user.getFavorite();
        if (!favorite.contains(house)) {
            favorite.add(house);
            user.setFavorite(favorite);
        }
        if (favoriteHistory.get(today) == null) {
            favoriteHistory.put(today, 1);
        } else {
            int numberOfFave = favoriteHistory.get(today);
            numberOfFave++;
            favoriteHistory.replace(today, numberOfFave);
        }
        house.setFavoriteHistory(favoriteHistory);
        house.setNoOfFave(house.getNoOfFave() + 1);
        repository.save(user);
        houseRepository.save(house);
    }

    public User getUserFavorite(UUID userid) {
        if (repository.existsById(userid)) {
            return repository.getOne(userid);
        } else {
            return null;
        }
    }

    public void removeFromFavorites(User user, House house) {
        List<House> favorite = user.getFavorite();
        favorite.remove(house);
        user.setFavorite(favorite);
        house.setNoOfFave(house.getNoOfFave() - 1);
        repository.save(user);
        houseRepository.save(house);
    }

    public void addToViewsHistory(House house, UUID userID) {
        Optional<User> user = getUserByUserID(userID);
        if(user.isPresent()){
            List<House> usersViewsHistory = user.get().getViewsHistory();
            usersViewsHistory.remove(house);
            usersViewsHistory.add(house);
            user.get().setViewsHistory(usersViewsHistory);
            Date today = new Date();
            Map<Date, Integer> viewsStatistics = house.getViewsHistory();
            if (viewsStatistics.get(today) == null) {
                viewsStatistics.put(today, house.getViews());
            } else {
                viewsStatistics.replace(today, house.getViews());
            }
            house.setViewsHistory(viewsStatistics);
            repository.save(user.get());
            houseRepository.save(house);
        }
    }
}
