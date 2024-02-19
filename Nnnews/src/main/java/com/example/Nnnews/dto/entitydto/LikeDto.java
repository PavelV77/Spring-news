package com.example.Nnnews.dto.entitydto;

import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@NoArgsConstructor
public class LikeDto {
    private UUID id;
    private ActivityTypeForLikes typeOfActivity;
    private UUID userId;
    private UUID newsId;
    private long insertAt;
    private long updateAt;

}
