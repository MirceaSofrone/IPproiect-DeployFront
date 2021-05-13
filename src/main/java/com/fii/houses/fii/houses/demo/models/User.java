package com.fii.houses.fii.houses.demo.models;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
//    @Column(columnDefinition = "BINARY(16)")
    private UUID userID;
    private Date creationDate;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @OneToMany
    private List<House> favorite = new ArrayList<>(); //for buyer
    @OneToMany
    private List<House> forSell = new ArrayList<>(); //seller
    public static final  Integer VIEWSHISTORYCAPACITY = 10;
    public static final Integer FAVOURITELISTCAPACITY = 20;
    @OneToMany
    private List<House> viewsHistory = new ArrayList<>(){
        @Override
        public boolean add(House house) {
            if(size()==VIEWSHISTORYCAPACITY){
                remove(0);
            }
            return super.add(house);
        }
    };



    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<House> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<House> favorite) {
        this.favorite = favorite;
    }

    public List<House> getViewsHistory() {
        return viewsHistory;
    }

    public void setViewsHistory(List<House> viewsHistory) {
        this.viewsHistory = viewsHistory;
    }

    public void addToViewsHistory(House house){
        viewsHistory.add(house);
    }

    public List<House> getForSell() {
        return forSell;
    }

    public void setForSell(List<House> forSell) {
        this.forSell = forSell;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
