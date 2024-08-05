package com.example.unogame.service;


import com.example.unogame.dto.gemini.Root;
import com.example.unogame.dto.request.PlayerRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface GeminiService {
    Root processUserRequest(PlayerRequestDto dto);

    Root getLatestResponse();
}
