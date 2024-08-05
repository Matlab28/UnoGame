package com.example.unogame.controller;

import com.example.unogame.dto.request.PlayerRequestDto;
import com.example.unogame.dto.response.PlayerResponseDto;
import com.example.unogame.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/uno")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService service;

    @PostMapping("/play-uno")
    public String create(@RequestBody PlayerRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping("/read-card-list")
    public ArrayList<String> readCards() {
        return service.readCards();
    }

    @GetMapping("/read-all-cards")
    public List<PlayerResponseDto> readAll() {
        return service.readAll();
    }

    @PostMapping("/play")
    public String play(@RequestParam String move) {
        return service.play(move);
    }

    @GetMapping("/read-your-card")
    public List<String> yourCard() {
        return service.yourCard();
    }
}
