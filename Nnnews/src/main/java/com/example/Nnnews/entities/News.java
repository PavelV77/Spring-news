package com.example.Nnnews.entities;


import com.example.Nnnews.types.entityfieldtypes.StatusTypeNews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "news",schema = "newsschema")
public class News extends BaseEntity {
    @Column(nullable = true)
    private String head;
    @Column(nullable = true, length = 65535)
    private String body;

    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::newsschema.status_type_news")
    @Column(name = "status", nullable = false)
    private StatusTypeNews status;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false, columnDefinition = "UUID")
    private User user;

    @OneToMany(mappedBy = "news", fetch = FetchType.LAZY)
    private List<Like> likesCollection = new ArrayList<>();

    @OneToMany(mappedBy = "news", fetch = FetchType.LAZY)
    private List<Comment> commentCollection = new ArrayList<>();

    @Formula(value = "(SELECT count(*) FROM newsschema.like l WHERE(l.news_id = id and l.type_of_activity = 'LIKE'))")
    private long countLike;
    @Formula(value = "(SELECT count(*) FROM newsschema.like l WHERE(l.news_id = id and l.type_of_activity = 'DISLIKE'))")
    private long countDislike;
    @Formula(value = "(SELECT count(*) FROM newsschema.like l WHERE(l.news_id = id and l.type_of_activity = 'VIEW'))")
    private long countView;

}
