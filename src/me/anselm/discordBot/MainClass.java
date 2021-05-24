package me.anselm.discordBot;

import me.anselm.discordBot.commands.CommandManager;
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
    public static final String prefix = "/";
    public static Guild guild;
    public static CommandManager commandManager;
    public static FileSaver fileSaver;
    public static JDA jda;

    public static void main(String[] args) throws IOException {
        JDABuilder jdaBuilder = JDABuilder.createDefault("ODQzODk4MTgwMTI2MjQ0OTE2.YKKjbQ.D5h9dmviSpbgEAo0DnQ4k77p9QY");
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
        for(Role role : member.getRoles()) {
            if(role.getName().equalsIgnoreCase(member.getEffectiveName())) {
                return role;
            }
        }
        if(!member.getGuild().getRoles().contains(member.getGuild().getRolesByName(member.getEffectiveName(), true))) {
            Role role = member.getGuild().createRole().setName(member.getEffectiveName()).complete();
            member.getGuild().addRoleToMember(member, role).queue();
            return role;
        }
        return member.getGuild().getRolesByName("null", true).get(0);
    }

}
