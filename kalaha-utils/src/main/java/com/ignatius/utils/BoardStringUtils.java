package com.ignatius.utils;

public enum BoardStringUtils {

    QUIT("Quit"),
    RESET("Reset"),
    GAME_RULES("?"),
    ;

    private final String string;

    private BoardStringUtils(String string) {
        this.string = string;
    }
    public  String getString() {
        return this.string;
    }
}
