package com.fii.houses.fii.houses.demo.service;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.models.UserActivity;
import com.fii.houses.fii.houses.demo.repository.HouseRepository;
import com.fii.houses.fii.houses.demo.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsersService {
    @Autowired
    private UserActivityRepository repository;
    @Autowired
    private HouseRepository houseRepository;

    public List<UserActivity> getAllUsers() {
        List<UserActivity> allUserActivities = repository.findAll();
        if (!allUserActivities.isEmpty()) {
            return allUserActivities;
        } else {
            return new ArrayList<>();
        }
    }

    public UserActivity getUserByUserID(UUID id) {
        List<UserActivity> allUserActivities = repository.findAll();
        for (UserActivity existingUserActivity : allUserActivities) {
            if (existingUserActivity.getUserID().equals(id)) {
                return existingUserActivity;
            }
        }
        return new UserActivity();
    }

    public UserActivity create(UserActivity userActivity) {
        userActivity.setUserID(UUID.randomUUID());
        userActivity.setCreationDate(new Date());
        userActivity = repository.save(userActivity);
        return userActivity;
    }

    public UserActivity update(UserActivity userActivity) {
        UUID id = userActivity.getUserID();
        UserActivity newUserActivity = new UserActivity();
        if (repository.existsById(id)) {
            newUserActivity = repository.findById(id).get();
            if (userActivity.getFirstName() != null) {
                newUserActivity.setFirstName(userActivity.getFirstName());
            }
            if (userActivity.getLastName() != null) {
                newUserActivity.setLastName(userActivity.getLastName());
            }
            repository.save(newUserActivity);
            return newUserActivity;
        } else {
            return null;
        }
    }

    public boolean deleteUser(UUID userid) {
        if (repository.existsById(userid)) {
            repository.deleteById(userid);
            return true;
        } else {
            return false;
        }
    }

    public void addToFavorites(UserActivity userActivity, House house) {
        Date today = new Date();
        Map<Date, Integer> favoriteHistory = house.getFavoriteHistory();
        List<House> favorite = userActivity.getFavorite();
        if (!favorite.contains(house)) {
            favorite.add(house);
            userActivity.setFavorite(favorite);
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
        repository.save(userActivity);
        houseRepository.save(house);
    }

    public UserActivity getUserFavorite(UUID userid) {
        if (repository.existsById(userid)) {
            return repository.getOne(userid);
        } else {
            return null;
        }
    }

    public void removeFromFavorites(UserActivity userActivity, House house) {
        List<House> favorite = userActivity.getFavorite();
        favorite.remove(house);
        userActivity.setFavorite(favorite);
        house.setNoOfFave(house.getNoOfFave() - 1);
        repository.save(userActivity);
        houseRepository.save(house);
    }

    public void addToViewsHistory(House house, UUID userID) {
        UserActivity userActivity = getUserByUserID(userID);
        Queue<House> viewsHistory = userActivity.getViewsHistory();
        viewsHistory.add(house);
        userActivity.setViewsHistory(viewsHistory);
        house.setViews(house.getViews() + 1);
        Date today = new Date();
        Map<Date, Integer> viewsStatistics = house.getViewsHistory();

        if (viewsStatistics.get(today) == null) {
            viewsStatistics.put(today, house.getViews() + 1);
        } else {
            viewsStatistics.replace(today, house.getViews() + 1);
        }
        house.setViewsHistory(viewsStatistics);
        house.setViews(house.getViews() + 1);
        repository.save(userActivity);
        houseRepository.save(house);
    }
}
