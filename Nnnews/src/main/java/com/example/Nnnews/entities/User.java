package com.example.Nnnews.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "\"user\"",schema = "newsschema")
public class User extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String login;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<News> newsCollection = new ArrayList<>();
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Like> likesCollection = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> commentCollection = new ArrayList<>();

}
