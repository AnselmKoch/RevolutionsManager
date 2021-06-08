package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.RoleManager;

import java.io.IOException;

public class NameCommand extends Command {

    public NameCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        RoleManager roleManager = null;
        boolean createNewRole = true;
        for(Role role : sender.getRoles()) {
            if(MainClass.fileManager.getSaveFileFromName("roles").getFileReader().containsString(role.getId())) {
                roleManager = role.getManager();
                roleManager.setName(message).queue();
                createNewRole = false;
            }
        }

        if(createNewRole) {
            Role role = guild.createRole().setName(message).complete();
            role.getManager().setHoisted(true).queue();
            guild.addRoleToMember(sender,role).queue();
            try {
                if(!MainClass.fileManager.getSaveFileFromName("roles").getFileReader().containsString(role.getId())) {
                    MainClass.fileManager.getSaveFileFromName("roles").getFileSaver().saveString(role.getId());
                    guild.modifyRolePositions().selectPosition(role).moveUp(1).queue();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
