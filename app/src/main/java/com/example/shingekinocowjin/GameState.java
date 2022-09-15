package com.example.shingekinocowjin;

public enum GameState {
    PLAYING,
    WELCOME,
    CONFIG,
    KEYBOARD,
    GAMEOVER;

    private static GameState gamestate = WELCOME;

    public static void setGameState(GameState state) {
        gamestate = state;
    }

    public static GameState getGamestate() {
        return gamestate;
    }
}
