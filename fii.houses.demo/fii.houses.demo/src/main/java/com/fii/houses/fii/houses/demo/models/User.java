package com.fii.houses.fii.houses.demo.models;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@Entity
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userID;
    private Date creationDate;
    @OneToMany
    private List<House> favorite = new ArrayList<>(); //for buyer
    @OneToMany
    private List<House> forSell = new ArrayList<>(); //seller
    @Transient
    private Queue<House> istoricVizionare = new ArrayBlockingQueue<House>(10){
        @Override
        public boolean add(House house){
            if(remainingCapacity() == 0)
                poll();
            else{
                offer(house);
            }
            return true;
        }
    }; //for buyer

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


    public Queue<House> getIstoricVizionare() {
        return istoricVizionare;
    }

    public void setIstoricVizionare(Queue<House> istoricVizionare) {
        this.istoricVizionare = istoricVizionare;
    }

    public List<House> getForSell() {
        return forSell;
    }

    public void setForSell(List<House> forSell) {
        this.forSell = forSell;
    }
}
