package com.example.Nnnews.dto.entitydto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Data
@NoArgsConstructor
public class CommentDto {
    private UUID id;
    private UUID newsId;
    private UUID userID;
    private String body;
    private long insertAt;
    private long updateAt;


}
