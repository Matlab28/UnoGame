package com.example.unogame.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player_infp")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_play")
    private String userPlay;

    @Column(name = "ai_turn")
    private String AITurn;
}
