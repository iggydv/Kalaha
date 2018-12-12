package com.ignatius.utils;

public enum BoardStringUtils {

    QUIT("Quit"),
    RESET("Reset"),
    GAME_RULES("?"),
    REGISTER_PLAYER_NAMES("Players Names"),
    PLAYER_1("Player 1"),
    PLAYER_2("Player 2"),
    LOCK("Lock"),
    START_GAME("Start Game")
    ;

    private final String string;

    private BoardStringUtils(String string) {
        this.string = string;
    }
    public  String getString() {
        return this.string;
    }
}
