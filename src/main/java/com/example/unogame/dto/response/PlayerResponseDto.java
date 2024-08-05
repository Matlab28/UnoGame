package com.example.unogame.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerResponseDto {
    private Long id;
    private String userPlay;
    private String AITurn;
}
