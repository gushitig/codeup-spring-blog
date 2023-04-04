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

    @Column(length=50, nullable = false, unique = true)
    private String username;

    @Column(length=100, nullable = false, unique = true)
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


    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }




}
