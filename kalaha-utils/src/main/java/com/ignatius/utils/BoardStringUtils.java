package com.ignatius.utils;

public enum BoardStringUtils {

    QUIT("Quit"),
    REGISTER_PLAYER_NAMES("Players Names"),
    PLAYER_1("Player 1"),
    PLAYER_2("Player 2"),
    LOCK("Lock"),
    START_GAME("Start Game"),
    KALAHA("kalaha"),
    PIT("pit"),
    WINNER("WINNER"),
    TIE("IT'S A TIE!"),
    RESET("Reset"),
    PLEASE_CLICK_RESET("Please click 'reset' play again"),
    RESET_BUTTON_CLICKED("Kalaha board reset!")
    ;

    private final String string;

    BoardStringUtils(String string) {
        this.string = string;
    }

    public  String getString() {
        return this.string;
    }
}
