package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.games.game.TikTakToe;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TikTakToeCommand extends Command  {

    public TikTakToeCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) throws IOException {

        if(!TikTakToe.running) {
            TikTakToe tikTakToe = new TikTakToe(textChannel);

        }else{
            textChannel.sendMessage("Ein Spiel ist bereits am laufen...").queue();
        }
    }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }


}
