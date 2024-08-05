package com.example.unogame.service;

import com.example.unogame.constant.Cards;
import com.example.unogame.dto.request.PlayerRequestDto;
import com.example.unogame.dto.response.PlayerResponseDto;
import com.example.unogame.entity.PlayerEntity;
import com.example.unogame.repository.PlayerRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@Getter
@RequiredArgsConstructor
public class PlayerService {
    private final ModelMapper modelMapper;
    private final PlayerRepository repository;
    Random random = new Random();
    ArrayList<String> cards = new ArrayList<>();

    public String create(PlayerRequestDto dto) {
        PlayerEntity entity = modelMapper.map(dto, PlayerEntity.class);

        for (int i = 0; i < 2; i++) {
            cards.add(cardGenerating());
        }
        cards.remove(6);
        cards.remove(14);

        log.info("Player played");
        repository.save(entity);
        return "Added successfully!";
    }

    public String play(String move) {
        List<String> firstSevenCards = cards.subList(0, Math.min(7, cards.size()));

        for (String game : firstSevenCards) {
            String card = game.toLowerCase();
            String substring = card.substring(card.indexOf(" ") + 1);
            if (move.toLowerCase().equals(substring)) {
                log.info("\"{}\" played", card);
                cards.remove(game);
                return "Card played: " + game;
            }
        }
        return "Wrong card";
    }

    public String cardGenerating() {
        int t = 1;
        String value = "";
        String color = "";

        for (int i = 1; i <= 4; i++) {
            int randomColor = random.nextInt(4);
            color = switch (randomColor) {
                case 0 -> Cards.GREEN.getValue();
                case 1 -> Cards.BLUE.getValue();
                case 2 -> Cards.YELLOW.getValue();
                case 3 -> Cards.RED.getValue();
                default -> throw new RuntimeException("Something went wrong...");
            };
        }

        for (int i = 1; i <= 7; i++) {
            int generator = random.nextInt(9);
            value = switch (generator) {
                case 0 -> t++ + ". " + Cards.RED.getValue() + " " + generator;
                case 1 -> t++ + ". " + Cards.GREEN.getValue() + " " + generator;
                case 2 -> t++ + ". " + Cards.YELLOW.getValue() + " " + generator;
                case 3 -> t++ + ". " + Cards.BLUE.getValue() + " " + generator;
                case 4 -> t++ + ". " + Cards.BLOCK.getValue();
                case 5 -> t++ + ". " + Cards.REVERSE.getValue();
                case 6 -> t++ + ". " + Cards.TWO_PLUS.getValue() + " " + color;
                case 7 -> t++ + ". " + Cards.FOUR_PLUS.getValue() + " " + color;
                case 8 -> t++ + ". " + Cards.COLOR_CHANGE.getValue();
                default -> "Server Error";
            };
            cards.add(value);
        }
        return value;
    }

    public ArrayList<String> readCards() {
        log.info("Card list responded.");
        return cards;
    }

    public List<String> yourCard() {
        return cards.subList(0, Math.min(7, cards.size()));
    }

    public List<PlayerResponseDto> readAll() {
        log.info("All cards responded.");
        return repository
                .findAll()
                .stream()
                .map(m -> modelMapper.map(m, PlayerResponseDto.class))
                .collect(Collectors.toList());
    }
}
