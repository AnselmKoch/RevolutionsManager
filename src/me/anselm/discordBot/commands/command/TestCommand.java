package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class TestCommand extends Command {

    public TestCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        textChannel.sendMessage("Test erfolgreich!!").queue();
    }
}
