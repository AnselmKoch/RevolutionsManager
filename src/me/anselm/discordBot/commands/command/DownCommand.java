package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.RoleManager;

import java.io.IOException;

public class DownCommand extends Command {
    public DownCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) throws IOException {
        Role role = Bot.getPlayerRole(sender);
        RoleManager roleManager = role.getManager();
        try {
            int posTemp = role.getPosition();
            guild.modifyRolePositions().selectPosition(role).moveTo(role.getPosition()-1).queue();
            System.out.println("Role Pos changed from " + posTemp + " to " + role.getPosition());
        }
        catch (IllegalArgumentException argumentException) {
            textChannel.sendMessage("FEHLER!!! LG").queue();
            System.out.println(argumentException.getMessage());
        }
    }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }
}
