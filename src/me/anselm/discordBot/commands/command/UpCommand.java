package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.RoleManager;

import java.io.IOException;

public class UpCommand extends Command {
    public UpCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) throws IOException {
        Role role = Bot.getPlayerRole(sender);
        RoleManager roleManager = role.getManager();
        try{
            guild.modifyRolePositions().selectPosition(role).moveUp(1).queue();
        }catch (Exception e) {
            textChannel.sendMessage("Die Position der Rolle ist bereits am Maximum...");
        }
    }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }
}
