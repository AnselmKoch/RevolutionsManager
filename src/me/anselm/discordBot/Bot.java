package me.anselm.discordBot;

import me.anselm.discordBot.commands.CommandManager;
import me.anselm.discordBot.file.FileManager;
import me.anselm.discordBot.listener.ReadyListener;
import me.anselm.discordBot.listener.ReceiveCommandListener;
import me.anselm.discordBot.listener.TikTakToeListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.RoleManager;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Bot extends ListenerAdapter {

    public static String commandFilePath = "commands.dc";
    public static String pokeFilePath = "pokeSpam.dc";
    public static String roleFilePath = "roles.dc";
    public static final String prefix = "/";
    public static Guild guild;
    public static CommandManager commandManager;
    public static FileManager fileManager;
    public static JDA jda;
    public static String jdaID = "843898180126244916";

    public static void main(String[] args) throws IOException {
        JDABuilder jdaBuilder = JDABuilder.createDefault("ODQzODk4MTgwMTI2MjQ0OTE2.YKKjbQ.EssDGrxhcpMrx6O4TbliXEHxolc");
        jdaBuilder.setActivity(Activity.watching("Sturm auf die Bastille"));
        jdaBuilder.addEventListeners(new ReceiveCommandListener());
        jdaBuilder.addEventListeners(new ReadyListener());
        jdaBuilder.addEventListeners(new TikTakToeListener());
        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }




    public static void updateRoleColor(int color, Role role) {
        RoleManager roleManager = role.getManager();
        roleManager.setColor(color).queue();
    }

    public static String pingMember(Member target) {
        return "<@" + target.getId() + ">";
    }

    public static Role getPlayerRole(Member member) {
        for(Role role : member.getRoles()) {
            if(fileManager.getSaveFileFromName("roles").getFileReader().containsString(role.getId())) {
                return role;
            }
        }

            Role role = guild.createRole().setName(member.getEffectiveName()).complete();
            role.getManager().setHoisted(true).queue();
            guild.addRoleToMember(member,role).queue();
            guild.modifyRolePositions().selectPosition(role).moveUp(1).queue();
            try {
                Bot.fileManager.getSaveFileFromName("roles").getFileSaver().saveString(role.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return role;

    }

    public static void sendMessage(TextChannel textChannel, String message) {
        textChannel.sendMessage(message).queue();
    }
    public static void sendMessageWithFile(TextChannel textChannel, String message, File file) {
        textChannel.sendMessage(message).addFile(file).queue();
    }

}
