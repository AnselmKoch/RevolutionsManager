package me.anselm.discordBot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.io.IOException;
import java.net.MalformedURLException;

public abstract class Command{

    private final String cmdName;
    private final int cmdID;
    private TextChannel textChannel;

    public Command(String cmdName, int cmdID) {
        this.cmdID = cmdID;
        this.cmdName = cmdName;
    }

    public abstract void execute(Member sender, String message, TextChannel textChannel, Guild guild) throws IOException;
    public abstract void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException;


    public void setTextChannel(TextChannel textChannel) {
        this.textChannel = textChannel;
    }
    public int getCmdID() {
        return cmdID;
    }

    public String getCmdName() {
        return cmdName;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }

}
