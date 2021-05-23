package me.anselm.discordBot.listener;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.CommandManager;
import me.anselm.discordBot.file.FileSaver;
import net.dv8tion.jda.api.GatewayEncoding;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class ReadyListener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent readyEvent) {
        MainClass.guild = readyEvent.getJDA().getGuilds().get(0);
        MainClass.commandManager = new CommandManager();
        try {
            MainClass.fileSaver = new FileSaver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
