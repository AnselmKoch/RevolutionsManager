package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class AvatarCommand extends Command {

    public AvatarCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) throws IOException {
    }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {
        for(Message.Attachment attachment : message.getAttachments()) {
            System.out.println(attachment.getFileName());
        }
    }
}
