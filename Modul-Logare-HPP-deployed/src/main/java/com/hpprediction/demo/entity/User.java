package com.hpprediction.demo.entity;

import com.fii.houses.fii.houses.demo.models.House;
import com.hpprediction.demo.datamodels.AuthProviderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String phoneNumber;

    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthProviderEnum provider;

    private String providerId;

    private boolean isEnabled;

    private boolean isLocked;

    private Date creationDate;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany
    private List<House> favorite = new ArrayList<>(); //for buyer

    @OneToMany
    private List<House> forSell = new ArrayList<>(); //seller
    public static final  Integer VIEWS_HISTORY_CAPACITY = 10;
    public static final Integer FAVOURITE_LIST_CAPACITY = 20;
    @OneToMany(fetch = FetchType.LAZY,
            targetEntity = House.class,
            cascade = CascadeType.ALL)
    private List<House> viewsHistory = new ArrayList<House>(){
        @Override
        public boolean add(House house) {
            if(size()== VIEWS_HISTORY_CAPACITY){
                remove(0);
            }
            return super.add(house);
        }
    };


    public User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String name,
                String phoneNumber,
                String username,
                String email,
                String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String name,
                String phoneNumber,
                String username,
                String email,
                String password,
                boolean isEnabled,
                boolean isLocked,
                Set<Role> roles) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isLocked = isLocked;
        this.roles = roles;
    }
    public long getUserID() {
        return id;
    }

    public void setUserID(long userID) {
        this.id = userID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


