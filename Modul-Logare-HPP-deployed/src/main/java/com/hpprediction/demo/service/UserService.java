package com.hpprediction.demo.service;

import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.repository.HouseRepository;
import com.hpprediction.demo.entity.User;
import com.hpprediction.demo.userapp.UserDetailsImplem;
import com.hpprediction.demo.repository.UserRepository;
import com.hpprediction.demo.entity.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final long MINUTES_TILL_EXPIRATION = 180;
    private static final String USER_NOT_FOUND =
            "Couldn't FIND an user with this email (%s)!";

    private final UserRepository userRepository;

    @Autowired
    private HouseRepository houseRepository;

    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));

        return UserDetailsImplem.build(user);
    }

    public Optional<User> loadAllUserData(String email) {

        return userRepository.findByEmail(email);
    }


    public void changeUserPassword(User user, String newPassword) {

        String hashedPassword = bCryptPasswordEncoder.encode(newPassword);
        userRepository.modifyPassword(user.getEmail(), hashedPassword);
    }

    public void createNewUserAfterOAuthLoginSucces(UserDetailsImplem user) {

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(MINUTES_TILL_EXPIRATION),
                new User(user.getUsername(), user.getEmail(), user.getPassword())
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        confirmationTokenService.setConfirmedAt(token);
    }

    public void enableAppUser(String email) {
        userRepository.enableAppUser(email);
    }
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        if (!allUsers.isEmpty()) {
            return allUsers;
        } else {
            return new ArrayList<>();
        }
    }

    public Optional<User> getUserByUserID(long id) {
        return userRepository.findById(id);
    }


    public User update(User user) {
        long id = user.getUserID();
        Optional<User>availableUser=userRepository.findById(id);
        User newUser;
        if (availableUser.isPresent()) {
            newUser = availableUser.get();
            if (user.getName() != null) {
                newUser.setName(user.getName());
            }
            if(user.getPhoneNumber() != null){
                newUser.setPhoneNumber(user.getPhoneNumber());
            }
            userRepository.save(newUser);
            return newUser;
        } else {
            return null;
        }
    }

    public boolean deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return userRepository.existsById(id);
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
        userRepository.save(user);
        houseRepository.save(house);
    }

    public User getUserFavorite(long userid) {
        if (userRepository.existsById(userid)) {
            return userRepository.getOne(userid);
        } else {
            return null;
        }
    }

    public void removeFromFavorites(User user, House house) {
        List<House> favorite = user.getFavorite();
        favorite.remove(house);
        user.setFavorite(favorite);
        house.setNoOfFave(house.getNoOfFave() - 1);
        userRepository.save(user);
        houseRepository.save(house);
    }

    public void addToViewsHistory(House house, long userID) {
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
            userRepository.save(user.get());
            houseRepository.save(house);
        }
    }
}

