package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PappeCommand extends Command {


    public PappeCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        BufferedImage bufferedImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
        Random random = new Random();
        int bitFlip = random.nextInt(16);
        int code = random.nextInt(5);
        if(bufferedImage.getGraphics() == null) {
            bufferedImage.createGraphics();
        }
        System.out.println(code);
        System.out.println(bitFlip);

        File file = new File("tiktaktoe.png");
        switch (code) {
            case 3: for (int i = 0; i < 500; i++) {
                     for (int j = 0; j < 500; j++) {
                            bufferedImage.setRGB(i, j, i * j << bitFlip);
                    }
            };
            case 1: for (int i = 500; i > 500; i++) {
                for (int j = 0; j < 500; j++) {
                    bufferedImage.setRGB(i, j, i + j << bitFlip);
                }
            };

            case 2: for (int i = 0; i < 500; i++) {
                for (int j = 0; j < 500; j++) {
                    bufferedImage.setRGB(i, j, i + j + j << bitFlip);
                }
            };
            case 0: for (int i = 0; i < 500; i++) {
                for (int j = 0; j < 500; j++) {
                    bufferedImage.setRGB(i, j, (i + j) / (i*j+1) << bitFlip);
                }
            };
            case 4: for (int i = 0; i < 500; i++) {
                for (int j = 0; j < 500; j++) {
                    bufferedImage.setRGB(i, j, (i * j * j ) / (i + j + 1)<< bitFlip);
                }
            };

        }
        try {
            ImageIO.write(bufferedImage,"png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textChannel.sendMessage("Hier deine Pappe...").addFile(file).queue();
    }
}
