package me.anselm.discordBot;

import me.anselm.discordBot.commands.CommandManager;
import me.anselm.discordBot.file.FileManager;
import me.anselm.discordBot.file.FileSaver;
import me.anselm.discordBot.listener.ReadyListener;
import me.anselm.discordBot.listener.ReceiveCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.RoleManager;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainClass extends ListenerAdapter {

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
        JDABuilder jdaBuilder = JDABuilder.createDefault("ODQzODk4MTgwMTI2MjQ0OTE2.YKKjbQ._HsLz93LankWzRlvl-OOxSMfMuA");
        jdaBuilder.setActivity(Activity.watching("Sturm auf die Bastille"));
        jdaBuilder.addEventListeners(new ReceiveCommandListener());
        jdaBuilder.addEventListeners(new ReadyListener());
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

    public static Role getColorRoleFromMember(Member member) {
        boolean createNewRole = true;
        for(Role role : member.getRoles()) {
            if(fileManager.getSaveFileFromName("roles").getFileReader().containsString(role.getId())) {
                createNewRole = false;
                return role;
            }
        }
          if(createNewRole) {
              Role role = guild.createRole().setName(member.getEffectiveName()).complete();
              guild.addRoleToMember(member,role).queue();
              return role;
          }
        return member.getGuild().getRolesByName("null", true).get(0);
    }

}
