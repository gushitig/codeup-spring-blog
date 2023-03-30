package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="blogger")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=50, nullable = false, unique = false)
    private String username;

    @Column(length=100, nullable = false)
    private String email;

    @Column(length=50, nullable = false)
    private String password;

    @Column(length=200)
    private String photo;

    @Column(length=500)
    private String bio;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blogger")
    @ToString.Exclude
    private List<Post> posts;




}
