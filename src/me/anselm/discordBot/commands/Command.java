package me.anselm.discordBot.commands;

import me.anselm.discordBot.listener.MessageReceiveListener;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;

public abstract class Command {

    private final String cmdName;
    private final int cmdID;
    private TextChannel textChannel;

    public Command(String cmdName, int cmdID) {
        this.cmdID = cmdID;
        this.cmdName = cmdName;
    }

    public abstract void execute(Member sender, String message, TextChannel textChannel, Guild guild);


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
