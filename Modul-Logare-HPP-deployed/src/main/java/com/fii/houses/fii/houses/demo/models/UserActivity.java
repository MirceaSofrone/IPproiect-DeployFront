package com.fii.houses.fii.houses.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@Entity
@Getter
@Setter
@Table(name = "usersactivity")
public class UserActivity {
    public static final  Integer VIEWSHISTORYCAPACITY = 10;
    public static final Integer FAVOURITELISTCAPACITY = 20;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Transient
    private Queue<House> viewsHistory = new ArrayBlockingQueue<House>(VIEWSHISTORYCAPACITY){
        @Override
        public boolean add(House house){
            if(remainingCapacity() == 0)
                poll();
            else{
                return offer(house);
            }
            return true;
        }
    }; //for buyer



}
