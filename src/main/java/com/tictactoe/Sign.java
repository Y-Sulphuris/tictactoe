package com.tictactoe;

public enum Sign {
    VOID(' '),
    CROSS('X'),
    NOUGHT('0');

    private final char ch;

    Sign(char ch) {
        this.ch = ch;
    }

    public char getCh() {
        return ch;
    }
}