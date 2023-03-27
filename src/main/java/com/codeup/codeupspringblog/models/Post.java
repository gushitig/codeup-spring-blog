package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=1024, nullable = false, unique = false)
    private String title;

    @Column(length=10000, nullable = false)
    private String body;

}
