package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="dogs")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=1024, nullable = false, unique = true)
    private String name;

    @Column(length=10, nullable = false)
    private String gender;

}