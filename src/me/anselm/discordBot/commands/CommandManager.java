package me.anselm.discordBot.commands;

import me.anselm.discordBot.MainClass;
import me.anselm.discordBot.commands.command.*;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;

public class CommandManager {

    private static ArrayList<Command> commands = new ArrayList<>();

    public CommandManager() {
        commands.add(new ColorCommand("farbe", 0));
        commands.add(new AddSpamCommand("addspam", 1));
        commands.add(new PappeCommand("pappe",2));
        commands.add(new BindTextCommand("bind", -2));
        commands.add(new UnbindTextCommand("unbind", -1));
        commands.add(new TestCommand("test", 321312));
        commands.add(new NameCommand("name", 3));
    }


    public static TextChannel getTextChannelFromID(String ID) {
        for(TextChannel textChannel : MainClass.guild.getTextChannels()) {
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
