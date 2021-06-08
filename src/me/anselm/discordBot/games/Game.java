package me.anselm.discordBot.games;

public abstract class Game {

    public Game() {
        this.start();
    }

    public abstract void start();

}
