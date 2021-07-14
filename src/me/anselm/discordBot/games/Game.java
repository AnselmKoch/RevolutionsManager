package me.anselm.discordBot.games;

import net.dv8tion.jda.api.entities.TextChannel;

public abstract class Game {

    private TextChannel gameChannel;

    public Game(TextChannel textChannel) {
        this.gameChannel = textChannel;
        this.start();
    }

    public abstract void start();

    public TextChannel getGameChannel() {
        return this.gameChannel;
    }
}
