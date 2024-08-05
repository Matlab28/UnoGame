package com.example.unogame.constant;

import lombok.Getter;

@Getter
public enum Cards {
    RED("Red"),
    BLUE("Blue"),
    GREEN("Green"),
    YELLOW("Yellow"),
    BLOCK("Block"),
    REVERSE("Reverse"),
    TWO_PLUS("2+"),
    FOUR_PLUS("4+"),
    COLOR_CHANGE("Color Change");

    private String value;

    Cards(String value) {
        this.value = value;
    }
}
