package me.anselm.discordBot.commands.command;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.Command;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;

public class ColorCommand extends Command {

    public ColorCommand(String cmdName, int cmdID) {
        super(cmdName, cmdID);
    }

    @Override
    public void execute(Member sender, String message, TextChannel textChannel, Guild guild) {
        System.out.println(sender.getEffectiveName() + " executed " + getCmdName() + " :  " + message);
        String[] messageCommand = message.split(" ");
            if (messageCommand.length == 1) {
                Role role = MainClass.getColorRoleFromMember(sender);
                if (messageCommand[0].contains(",")) {
                    String RGBString = messageCommand[0];
                    String[] rgbvalues = RGBString.split(",");
                    if (rgbvalues.length == 3) {
                        try {
                            int red = Integer.valueOf(rgbvalues[0]);
                            int green = Integer.valueOf(rgbvalues[1]);
                            int blue = Integer.valueOf(rgbvalues[2]);

                            Color color = new Color(red, green, blue);
                            MainClass.updateRoleColor(color.getRGB(), role);
                        } catch (NumberFormatException e) {

                        }
                    }
                } else {
                    try {
                        int rgbValue = Integer.valueOf(messageCommand[0]);
                        MainClass.updateRoleColor(rgbValue, role);
                    } catch (NumberFormatException numberFormatException) {

                        switch (messageCommand[0]) {
                            case "blau":
                                MainClass. updateRoleColor(Color.BLUE.getRGB(), role);
                                break;
                            case "schwarz":
                                MainClass.updateRoleColor(15, role);
                                break;
                            case "rot":
                                MainClass.updateRoleColor(Color.RED.getRGB(), role);
                                break;
                            case "grün":
                                MainClass. updateRoleColor(Color.GREEN.getRGB(), role);
                                break;
                            case "gelb":
                                MainClass.updateRoleColor(Color.YELLOW.getRGB(), role);
                                break;
                            case "magenta":
                                MainClass. updateRoleColor(Color.MAGENTA.getRGB(), role);
                                break;
                            case "grau":
                                MainClass. updateRoleColor(Color.gray.getRGB(), role);
                                break;
                            case "weiß":
                                MainClass. updateRoleColor(Color.white.getRGB(), role);
                                break;
                            case "türkis":
                                MainClass. updateRoleColor(Color.cyan.getRGB(), role);
                                break;
                            case "orange":
                                MainClass.updateRoleColor(Color.orange.getRGB(), role);
                                break;
                            case "pink":
                                MainClass. updateRoleColor(Color.PINK.getRGB(), role);
                                break;
                        }
                    }
                }
            }
        }
    }
