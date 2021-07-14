package me.anselm.discordBot.commands;

import me.anselm.discordBot.Bot;
import me.anselm.discordBot.commands.command.*;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;

public class CommandManager {

    private static ArrayList<Command> commands = new ArrayList<>();

    public CommandManager() {
        commands.add(new ColorCommand("farbe", 0));
        commands.add(new AddSpamCommand("addspam", 1));
        commands.add(new ShapePicCommand("shapepic",2));
        commands.add(new TikTakToeCommand("tiktaktoe",4));
        commands.add(new BindTextCommand("bind", -2));
        commands.add(new UnbindTextCommand("unbind", -1));
        commands.add(new TestCommand("test", 321312));
        commands.add(new NameCommand("name", 3));
        commands.add(new UpCommand("up",5));
        commands.add(new DownCommand("down", 6));
        commands.add(new DrawCommand("draw", 9));
        commands.add(new DeleteCommand("delete", 10));
        commands.add(new AvatarCommand("avatar", 11));
        commands.add(new ZitatCommand("zitat", 12));
    }


    public static TextChannel getTextChannelFromID(String ID) {
        for(TextChannel textChannel : Bot.guild.getTextChannels()) {
            if(textChannel.getId().equalsIgnoreCase(ID)) {
                return textChannel;
            }
        }
        return null;
    }

    public static Command getCommandFromTextCH(TextChannel textChannel) {
      for(Command command : commands) {
          if((command.getTextChannel() != null) && (command.getTextChannel().getId().equalsIgnoreCase(textChannel.getId()))) {
              return command;
          }
      }
          return null;
    }

    public static Command getCommandFromName(String name){
        for(Command command : commands) {
            if(name.equalsIgnoreCase(command.getCmdName())) {
                return command;
            }
        }
        return null;
    }

    public static ArrayList<Command> getCommands() {
        return commands;
    }
}
