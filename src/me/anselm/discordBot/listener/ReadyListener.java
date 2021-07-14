package me.anselm.discordBot.listener;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.CommandManager;
import me.anselm.discordBot.file.FileManager;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class ReadyListener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent readyEvent) {
        Bot.guild = readyEvent.getJDA().getGuilds().get(0);
        Bot.commandManager = new CommandManager();
        try {
                Bot.fileManager = new FileManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
