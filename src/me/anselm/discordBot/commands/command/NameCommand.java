package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.RoleManager;

import java.io.IOException;

public class NameCommand extends Command {

    public NameCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        RoleManager roleManager = null;
        Role role = Bot.getPlayerRole(sender);
        if(role != null) {
            roleManager = role.getManager();
            System.out.println("HALLO");
            roleManager.setName(message).queue();
        }
    }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }
}
