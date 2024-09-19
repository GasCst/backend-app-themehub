package com.themehub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.themehub.constant.ThemehubConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = ThemehubConstant.TAB_NAME_USER, schema =ThemehubConstant.SEQ_NAME_USER)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username",nullable = false, unique = true, length = 50)
    private String username;

    @Column(name="email",nullable = false, unique = true, length = 100)
    private String email;

    @Column(name="password",nullable = false, length = 255)
    private String password;

    @Column(name="first_name",length = 50)
    private String firstName;

    @Column(name="last_name",length = 50)
    private String lastName;

    @Column(name="company",length = 100)
    private String company;

    @Column(name="state",length = 20, nullable = false, columnDefinition = "varchar(20) DEFAULT 'customer'")
    private String state;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Purchase> purchases;


}
