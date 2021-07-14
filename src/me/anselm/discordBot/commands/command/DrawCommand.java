package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import me.anselm.discordBot.utils.GifSequenceWriter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawCommand extends Command {

    public DrawCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        File file = new File("gif.gif");
        ImageOutputStream output = new FileImageOutputStream(file);

        GifSequenceWriter gifSequenceWriter = new GifSequenceWriter(output, bufferedImage.getType(), 1, true);

        // write out the first image to our sequence...
        gifSequenceWriter.writeToSequence(bufferedImage);
        graphics.setColor(Color.white);
        double x= -5D;
        for (int i = 0; i < 100; i++) {
            int value = (int) (50 + Math.pow((double) x, 2.0D));
            if (value < 100) {
                bufferedImage.setRGB(i, value, Color.WHITE.getRGB());
                if(i % 3 == 0) {
                    gifSequenceWriter.writeToSequence(bufferedImage);
                }
                x += 0.1D;
            }
            System.out.println(value);
            }

            gifSequenceWriter.close();
            output.close();

            Bot.sendMessageWithFile(textChannel, "Mathe mois:", file);
        }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }

}
