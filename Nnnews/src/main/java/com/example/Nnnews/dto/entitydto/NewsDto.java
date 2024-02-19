package com.example.Nnnews.dto.entitydto;

import com.example.Nnnews.entities.User;
import com.example.Nnnews.types.entityfieldtypes.StatusTypeNews;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.UUID;

@Data
@NoArgsConstructor
public class NewsDto {
    private UUID id;
    private String head;
    private String body;
    private UUID userId;
    private long insertedAt;
    private long updatedAt;
    private StatusTypeNews status;
    private long countLike;
    private long countDislike;
    private long countView;
}
