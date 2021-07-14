package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ZitatCommand extends Command {
    public ZitatCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new File("albert.jpg"));

        File zitatFile = new File("zitat.png");
        String[] messages = message.split(" ");
        ArrayList<String> words = new ArrayList<>();

        for(String string : messages) {
            words.add(string);
        }

        if(bufferedImage.getGraphics() == null) {
            bufferedImage.createGraphics();
        }
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();

        graphics2D.setColor(new Color(255,255,255));
        int x = 10;
        int y = 50;

        graphics2D.setFont(new Font("Forte",Font.PLAIN, 25));
        for(String word : words) {
            graphics2D.drawString(word, x, y);
            for (int i = 0; i < word.length(); i++) {
                x += 18;
            }
            if (x >= 2000 && y < 200) {
                x = 10;
                y += 50;
            }
        }
        ImageIO.write(bufferedImage, "png", zitatFile);
        Bot.sendMessageWithFile(textChannel, "Zitat...", zitatFile);
    }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }
}
