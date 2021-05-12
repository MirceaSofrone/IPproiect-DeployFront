package com.hpprediction.demo.entity;

import com.hpprediction.demo.datamodels.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRoleEnum name;

    public Role(){

    }

    public Role(Integer id, UserRoleEnum name) {
        this.id = id;
        this.name = name;
    }
}