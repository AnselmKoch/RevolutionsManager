package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.io.IOException;

public class ColorCommand extends Command {

    public ColorCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        System.out.println(sender.getEffectiveName() + " executed " + getCmdName() + " :  " + message);
        String[] messageCommand = message.split(" ");
            if (messageCommand.length == 1) {
                Role role = Bot.getPlayerRole(sender);

                if (messageCommand[0].contains(",")) {
                    String RGBString = messageCommand[0];
                    String[] rgbvalues = RGBString.split(",");
                    if (rgbvalues.length == 3) {
                        try {
                            int red = Integer.valueOf(rgbvalues[0]);
                            int green = Integer.valueOf(rgbvalues[1]);
                            int blue = Integer.valueOf(rgbvalues[2]);

                            Color color = new Color(red, green, blue);
                            Bot.updateRoleColor(color.getRGB(), role);
                        } catch (NumberFormatException e) {

                        }
                    }
                } else {
                    try {
                        int rgbValue = Integer.valueOf(messageCommand[0]);
                        Bot.updateRoleColor(rgbValue, role);
                    } catch (NumberFormatException numberFormatException) {

                        switch (messageCommand[0].toLowerCase()) {
                            case "blau":
                                Bot. updateRoleColor(Color.BLUE.getRGB(), role);
                                break;
                            case "schwarz":
                                Bot.updateRoleColor(15, role);
                                break;
                            case "rot":
                                Bot.updateRoleColor(Color.RED.getRGB(), role);
                                break;
                            case "grün":
                                Bot. updateRoleColor(Color.GREEN.getRGB(), role);
                                break;
                            case "gelb":
                                Bot.updateRoleColor(Color.YELLOW.getRGB(), role);
                                break;
                            case "magenta":
                                Bot. updateRoleColor(Color.MAGENTA.getRGB(), role);
                                break;
                            case "grau":
                                Bot. updateRoleColor(Color.gray.getRGB(), role);
                                break;
                            case "weiß":
                                Bot. updateRoleColor(Color.white.getRGB(), role);
                                break;
                            case "türkis":
                                Bot. updateRoleColor(Color.cyan.getRGB(), role);
                                break;
                            case "orange":
                                Bot.updateRoleColor(Color.orange.getRGB(), role);
                                break;
                            case "pink":
                                Bot. updateRoleColor(Color.PINK.getRGB(), role);
                                break;
                        }
                    }
                }
            }
        }

    @Override
    public void execute(Member sender, Message message, TextChannel textChannel, Guild guild) throws IOException {

    }
}
