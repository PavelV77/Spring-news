package com.example.Nnnews.entities;


//import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comment", schema = "newsschema")
public class Comment extends BaseEntity {

    @Column(name = "body", length = 65535)
    private String body;
    @ManyToOne
    @JoinColumn(name = "user_id",/*referencedColumnName = "id", nullable = false,*/ columnDefinition = "UUID"/*, insertable = false,updatable = false*/)
    private User user;
    @ManyToOne
    @JoinColumn(name = "news_id",/*referencedColumnName = "id", nullable =false,*/ columnDefinition = "UUID"/*,insertable = false,updatable = false*/)
    private News news;

}
