package com.example.Nnnews.entities;

import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
//import jakarta.persistence.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "like",schema = "newsschema")
public class Like extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::newsschema.activity_type_for_likes")
    @Column(name = "type_of_activity",nullable = false, columnDefinition = "newsschema.activity_type_for_likes")
    private ActivityTypeForLikes typeOfActivity;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false, columnDefinition = "UUID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "news_id",referencedColumnName = "id", nullable =false, columnDefinition = "UUID")
    private News news;
}
