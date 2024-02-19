package com.example.Nnnews.dto.entitydto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String login;
    private long insertedAt;
    private long updatedAt;

}
